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
import com.iotoms.utils.extensions.getOrZero

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
        }
        val cartItemEntity = CartItemEntity(
            id = System.currentTimeMillis().toString(),
            itemId = itemEntity.itemId.orEmpty(),
            upc = listOf(itemEntity.upc.orEmpty()),
            name = itemEntity.itemName.orEmpty(),
            price = itemEntity.sellingPrice ?: 0.0,
            quantity = 1.0,
            transactionType = cartEntity.transactionType,
            transactionNumber = cartEntity.transactionNumber,
            imageUrl = itemEntity.imageGallery?.imageUrl
        )

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

    private fun createCart(): CartEntity {
        return CartEntity(
            transactionNumber = System.currentTimeMillis().toString(),
            transactionType = TransactionType.SALE,
            total = 0.0,
            amountDue = 0.0,
            amountPaid = 0.0,
            tax = 0.0,
            discount = 0.0,
            customerNumber = ""
        )
    }
}