package com.iotoms.data.local.dao

import androidx.paging.PagingSource
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

    @Query("SELECT * FROM items WHERE itemId = :itemId LIMIT 1")
    suspend fun getItemById(itemId: String): ItemEntity?

    @Query("DELETE FROM items")
    suspend fun clearItems()

    @Query("DELETE FROM items WHERE itemId = :itemId")
    suspend fun deleteItemById(itemId: Int)

    @Query("SELECT * FROM items")
    fun getPaginatedItems(): PagingSource<Int, ItemEntity>

    @Query("SELECT * FROM items WHERE itemId IN (:ids)")
    fun getPaginatedItemsByItemIds(ids: List<String>): PagingSource<Int, ItemEntity>

}