package com.iotoms.data.local.dao

import androidx.paging.PagingData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.ItemEntity

/**
 * Created by Fasil on 21/12/2025
 */
@Dao
interface ItemDao {
    @Upsert
    suspend fun insertItems(itemEntities: List<ItemEntity>)

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<ItemEntity>

    @Query("SELECT * FROM items WHERE id = :itemId LIMIT 1")
    suspend fun getItemById(itemId: Int): ItemEntity?

    @Query("DELETE FROM items")
    suspend fun clearItems()

    @Query("DELETE FROM items WHERE id = :itemId")
    suspend fun deleteItemById(itemId: Int)

    @Query("SELECT * FROM items")
    suspend fun getPaginatedItems(): PagingData<List<ItemEntity>>
}