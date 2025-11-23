package com.iotoms.data.repository

import com.iotoms.data.model.response.PayModeResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.PayModeRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Fasil on 23/11/2025
 */
class PayModeRepositoryImpl(private val client: HttpClient): PayModeRepository {
    override suspend fun getPayModes(): Result<List<PayModeResponse>, ApiError> {
        return apiRequest<List<PayModeResponse>> {
            client.get(ApiUrl.PAY_MODES)
        }
    }
}