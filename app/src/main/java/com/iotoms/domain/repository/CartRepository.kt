package com.iotoms.domain.repository

import com.iotoms.data.local.entity.CartEntity
import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.data.local.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 30/11/2025
 */
interface CartRepository {
    suspend fun addItemToCart(itemEntity: ItemEntity)
    suspend fun addGeneralItemToCart(name: String, price: Double)
    suspend fun updateQuantity(cartItemEntity: CartItemEntity)
    suspend fun deleteCartItem(cartItemEntity: CartItemEntity)
    suspend fun clearCart()
    suspend fun calculateTotals()
    fun getCart(): Flow<CartEntity?>
    suspend fun getCartItems(transactionNumber: String): List<CartItemEntity>
}