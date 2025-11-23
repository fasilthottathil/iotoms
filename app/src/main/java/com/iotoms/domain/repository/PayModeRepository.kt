package com.iotoms.domain.repository

import com.iotoms.data.model.response.PayModeResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result

/**
 * Created by Fasil on 23/11/2025
 */
interface PayModeRepository {
    suspend fun getPayModes(): Result<List<PayModeResponse>, ApiError>
}