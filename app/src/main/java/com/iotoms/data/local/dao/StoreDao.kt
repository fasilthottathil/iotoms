package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.StoreEntity

/**
 * Created by Fasil on 26/12/2025
 */
@Dao
interface StoreDao {
    @Upsert
    suspend fun insertStores(stores: List<StoreEntity>)

    @Query("SELECT * FROM stores")
    suspend fun getAllStores(): List<StoreEntity>

    @Query("DELETE FROM stores")
    suspend fun deleteAllStores()

    @Query("SELECT * FROM stores WHERE id = :id")
    suspend fun getStoreById(id: Int): StoreEntity?

    @Query("DELETE FROM stores WHERE id = :id")
    suspend fun deleteStoreById(id: Int)

}