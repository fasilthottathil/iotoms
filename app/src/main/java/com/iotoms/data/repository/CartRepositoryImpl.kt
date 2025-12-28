package com.iotoms.data.repository

import com.iotoms.data.enum.DiscountApplyMode
import com.iotoms.data.enum.DiscountApplyType
import com.iotoms.data.enum.DiscountType
import com.iotoms.data.enum.TransactionType
import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.local.entity.CartEntity
import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.model.CartDiscount
import com.iotoms.domain.repository.CartRepository
import com.iotoms.domain.repository.DiscountRepository
import com.iotoms.domain.repository.TaxRepository
import com.iotoms.utils.constants.Constants.GENERAL_ITEM_ID
import com.iotoms.utils.extensions.getOrZero
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 30/11/2025
 */
class CartRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val taxRepository: TaxRepository,
    private val discountRepository: DiscountRepository
) : CartRepository {
    override suspend fun addItemToCart(itemEntity: ItemEntity) {
        var cartEntity = appDatabase.cartDao().getCart()
        if (cartEntity == null) {
            cartEntity = createCart()
        } else {
            val cartItemEntity =
                appDatabase.cartDao().getCartItemByItemIdAndTxnNumberAndSellPrice(
                    transactionNumber = cartEntity.transactionNumber,
                    itemId = itemEntity.itemId,
                    sellingPrice = itemEntity.sellingPrice.getOrZero()
                )
            if (cartItemEntity != null && itemEntity.itemId != GENERAL_ITEM_ID) {
                cartItemEntity.quantity += 1.0
                updateQuantity(cartItemEntity)
                return
            }
        }
        val cartItemEntity = CartItemEntity(
            id = System.currentTimeMillis().toString(),
            itemId = itemEntity.itemId,
            upc = listOf(itemEntity.upc.orEmpty()),
            name = itemEntity.itemName.orEmpty(),
            price = itemEntity.sellingPrice ?: 0.0,
            quantity = 1.0,
            transactionType = cartEntity.transactionType,
            transactionNumber = cartEntity.transactionNumber,
            imageUrl = itemEntity.imageGallery?.imageUrl,
            total = itemEntity.sellingPrice.getOrZero(),
            taxIds = itemEntity.taxIds ?: emptyList()
        )

        calculateTaxAndDiscounts(cartItemEntity, itemEntity)

        cartItemEntity.total = (cartItemEntity.price * cartItemEntity.quantity) + cartItemEntity.tax - cartItemEntity.discount

        appDatabase.cartDao().upsertCartItem(cartItemEntity)
        calculateTotals()

    }

    override suspend fun addGeneralItemToCart(name: String, price: Double) {
        val itemEntity = requireNotNull(appDatabase.itemDao().getItemById(GENERAL_ITEM_ID)) {
            "General item not found in database."
        }
        itemEntity.sellingPrice = price
        itemEntity.itemName = name
        addItemToCart(itemEntity)
    }

    private suspend fun calculateTaxAndDiscounts(
        cartItemEntity: CartItemEntity,
        itemEntity: ItemEntity
    ) {
        if (itemEntity.taxIds.isNullOrEmpty().not()) {
            itemEntity.taxIds.forEach { taxId ->
                taxRepository.calculateTax(taxId, itemEntity).also { it ->
                    if (it > 0) {
                        cartItemEntity.tax += it
                        cartItemEntity.taxIds = if (cartItemEntity.taxIds.isEmpty()) {
                            listOf(taxId)
                        } else {
                            cartItemEntity.taxIds + taxId
                        }
                    }
                }
            }
        }

        if (itemEntity.discountIds.isNullOrEmpty().not()) {
            itemEntity.discountIds.forEach { id ->
                discountRepository.calculateDiscount(id).also {
                    if (it > 0) {
                        cartItemEntity.discount += it
                        val discount = discountRepository.getDiscountById(id)
                        if (discount != null) {
                            cartItemEntity.cartDiscounts = cartItemEntity.cartDiscounts + CartDiscount(
                                discountId = discount.id.getOrZero(),
                                description = discount.name.orEmpty(),
                                discount = it,
                                discountRate = discount.rate.getOrZero(),
                                discountType = DiscountType.fromId(discount.discountType.getOrZero()),
                                discountApplyType = DiscountApplyType.NORMAL,
                                applyMode = DiscountApplyMode.ITEM_LEVEL
                            )
                        }
                    }
                }
            }
        }
    }

    override suspend fun updateQuantity(cartItemEntity: CartItemEntity) {
        val cartEntity = requireNotNull(appDatabase.cartDao().getCart()) {
            "Cart not found when updating quantity for itemId: ${cartItemEntity.itemId}"
        }
        if (cartItemEntity.quantity <= 0) {
            deleteCartItem(cartItemEntity)
            return
        }
        val cartItemEntityFromDb = requireNotNull(appDatabase.cartDao().getCartItemByItemIdAndTxnNumberAndSellPrice(
            cartEntity.transactionNumber,
            cartItemEntity.itemId,
            cartItemEntity.price
        )) {
            "Cart item not found for itemId: ${cartItemEntity.itemId} and transactionNumber: ${cartEntity.transactionNumber}"
        }

        cartItemEntityFromDb.quantity = cartItemEntity.quantity
        val itemEntity = requireNotNull(appDatabase.itemDao().getItemById(cartItemEntity.itemId)) {
            "Item not found for itemId: ${cartItemEntity.itemId}"
        }
        calculateTaxAndDiscounts(cartItemEntityFromDb, itemEntity)
        cartItemEntity.total = (cartItemEntityFromDb.price * cartItemEntityFromDb.quantity) + cartItemEntityFromDb.tax - cartItemEntityFromDb.discount

        appDatabase.cartDao().upsertCartItem(cartItemEntity)
        calculateTotals()
    }

    override suspend fun deleteCartItem(cartItemEntity: CartItemEntity) {
        appDatabase.cartDao().deleteCartItem(cartItemEntity)
        calculateTotals()
    }

    override suspend fun clearCart() {
        appDatabase.cartDao().clearCart()
    }

    override fun getCart(): Flow<CartEntity?> {
        return appDatabase.cartDao().getCartFlow()
    }

    override suspend fun getCartItems(transactionNumber: String): List<CartItemEntity> {
        return appDatabase.cartDao().getCartItems(transactionNumber)
    }

    override suspend fun calculateTotals() {
        val cartEntity = appDatabase.cartDao().getCart()
        val cartItems = appDatabase.cartDao().getCartItems(cartEntity?.transactionNumber.orEmpty())
        if (cartItems.isNotEmpty()) {
            val total = cartItems.sumOf { it.price * it.quantity }
            val tax = cartItems.sumOf { it.tax }
            val discount = cartItems.sumOf { it.discount }
            val amountDue = total + tax - discount

            val updatedCart = cartEntity?.copy(
                total = total,
                tax = tax,
                discount = discount,
                amountDue = amountDue
            )
            if (updatedCart != null) {
                appDatabase.cartDao().upsertCart(updatedCart)
            }
        } else {
            if (cartEntity != null) {
                appDatabase.cartDao().upsertCart(
                    cartEntity.copy(
                        total = 0.0,
                        tax = 0.0,
                        discount = 0.0,
                        amountDue = 0.0
                    )
                )
            }
        }
    }

    private suspend fun createCart(): CartEntity {
        return CartEntity(
            transactionNumber = System.currentTimeMillis().toString(),
            transactionType = TransactionType.SALE,
            total = 0.0,
            amountDue = 0.0,
            amountPaid = 0.0,
            tax = 0.0,
            discount = 0.0,
            customerNumber = System.currentTimeMillis().toString()
        ).also {
            appDatabase.cartDao().upsertCart(it)
        }
    }
}