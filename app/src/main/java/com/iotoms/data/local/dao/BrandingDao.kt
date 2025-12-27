package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.BrandingEntity

/**
 * Created by Fasil on 27/12/2025
 */
@Dao
interface BrandingDao {
    @Upsert
    suspend fun insertBranding(branding: List<BrandingEntity>)

    @Query("SELECT * FROM branding")
    suspend fun getAllBranding(): List<BrandingEntity>

    @Query("DELETE FROM branding")
    suspend fun deleteAllBranding()

    @Query("SELECT * FROM branding WHERE storeId = :storeId")
    suspend fun getBrandingByStoreId(storeId: Int): BrandingEntity?

    @Query("SELECT * FROM branding WHERE id = :id")
    suspend fun getBrandingById(id: Int): BrandingEntity?

    @Query("DELETE FROM branding WHERE id = :id")
    suspend fun deleteBrandingById(id: Int)
}