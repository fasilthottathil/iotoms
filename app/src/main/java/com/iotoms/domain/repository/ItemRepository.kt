package com.iotoms.domain.repository

import androidx.paging.PagingData
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.model.request.AddItemRequest
import com.iotoms.data.model.response.ItemResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 25/12/2025
 */
interface ItemRepository {
    suspend fun getAllItemsPaginated(page: Int): Result<List<Boolean>, ApiError>
    fun getPaginateItemsFromLocal(): Flow<PagingData<ItemEntity>>
    fun getPaginateItemsByItemIdsFromLocal(ids: List<String>): Flow<PagingData<ItemEntity>>
    fun searchItemsFromLocal(query: String): Flow<PagingData<ItemEntity>>
    fun getItemByItemId(itemId: String): Flow<ItemEntity?>
    suspend fun addItem(addItemRequest: AddItemRequest): Result<ItemResponse, ApiError>
}