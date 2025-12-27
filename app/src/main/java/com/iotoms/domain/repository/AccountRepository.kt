package com.iotoms.domain.repository

import com.iotoms.data.model.response.PlansResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result

/**
 * Created by Fasil on 26/12/2025
 */
interface AccountRepository {
    suspend fun getAllPlans(): Result<PlansResponse, ApiError>
}