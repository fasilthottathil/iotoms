package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.DiscountEntity

/**
 * Created by Fasil on 21/12/2025
 */
@Dao
interface DiscountDao {
    @Upsert
    suspend fun upsertDiscount(discountEntity: DiscountEntity)

    @Query("SELECT * FROM discounts WHERE id = :id")
    suspend fun getDiscountById(id: Int): DiscountEntity?

    @Query("SELECT * FROM discounts WHERE status = 'Active'")
    suspend fun getAllActiveDiscounts(): List<DiscountEntity>

    @Query("DELETE FROM discounts WHERE id = :id")
    suspend fun deleteDiscountById(id: Int)

    @Query("DELETE FROM discounts")
    suspend fun clearAllDiscounts()
}