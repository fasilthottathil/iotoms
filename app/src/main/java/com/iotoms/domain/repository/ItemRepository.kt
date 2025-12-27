package com.iotoms.domain.repository

import androidx.paging.PagingData
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 25/12/2025
 */
interface ItemRepository {
    suspend fun getAllItemsPaginated(page: Int): Result<List<Boolean>, ApiError>
    fun getPaginateItemsFromLocal(): Flow<PagingData<ItemEntity>>
}