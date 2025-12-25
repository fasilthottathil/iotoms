package com.iotoms.domain.repository

import com.iotoms.data.model.response.ItemResponse
import com.iotoms.data.model.response.PageResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result

/**
 * Created by Fasil on 25/12/2025
 */
interface ItemRepository {
    suspend fun getAllItemsPaginated(page: Int): Result<List<Boolean>, ApiError>
}