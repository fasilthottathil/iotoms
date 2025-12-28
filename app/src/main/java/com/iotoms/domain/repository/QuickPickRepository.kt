package com.iotoms.domain.repository

import com.iotoms.data.local.entity.QuickPickEntity
import com.iotoms.data.model.response.QuickPickResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 28/12/2025
 */
interface QuickPickRepository {
    suspend fun getAllQuickPicks(): Result<QuickPickResponse, ApiError>
    fun getQuickPicksFromDb() : Flow<List<QuickPickEntity>>
}