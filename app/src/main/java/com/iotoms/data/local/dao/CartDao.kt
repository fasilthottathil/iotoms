package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.iotoms.data.local.entity.CartEntity
import com.iotoms.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 30/11/2025
 */
@Dao
interface CartDao {
    @Upsert
    suspend fun upsertCart(cartEntity: CartEntity)

    @Query("SELECT * FROM cart LIMIT 1")
    suspend fun getCart(): CartEntity?

    @Query("SELECT * FROM cart LIMIT 1")
    fun getCartFlow(): Flow<CartEntity?>

    @Upsert
    suspend fun upsertCartItem(cartItemEntity: CartItemEntity)

    @Query("SELECT * FROM cart_items WHERE transactionNumber = :transactionNumber")
    suspend fun getCartItems(transactionNumber: String): List<CartItemEntity>

    @Query("SELECT * FROM cart_items WHERE transactionNumber = :transactionNumber")
    fun getCartItemsFlow(transactionNumber: String): Flow<List<CartItemEntity>>

    @Query("SELECT * FROM cart_items WHERE transactionNumber = :transactionNumber AND itemId = :itemId LIMIT 1")
    suspend fun getCartItemByItemIdAndTxnNumber(transactionNumber: String, itemId: String): CartItemEntity?

    @Query("SELECT * FROM cart_items WHERE transactionNumber = :transactionNumber AND itemId = :itemId AND price = :sellingPrice LIMIT 1")
    suspend fun getCartItemByItemIdAndTxnNumberAndSellPrice(transactionNumber: String, itemId: String, sellingPrice: Double): CartItemEntity?

    @Delete
    suspend fun deleteCartItem(cartItemEntity: CartItemEntity)

    @Query("DELETE FROM cart")
    suspend fun clearCartTable()

    @Query("DELETE FROM cart_items")
    suspend fun clearCartItemsTable()

    @Transaction
    suspend fun clearCart() {
        clearCartTable()
        clearCartItemsTable()
    }

}