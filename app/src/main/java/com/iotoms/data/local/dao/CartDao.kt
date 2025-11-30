package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.iotoms.data.local.entity.CartItemEntity

/**
 * Created by Fasil on 30/11/2025
 */
@Dao
interface CartDao {
    @Upsert
    suspend fun upsertCartItem(cartItemEntity: CartItemEntity)
}