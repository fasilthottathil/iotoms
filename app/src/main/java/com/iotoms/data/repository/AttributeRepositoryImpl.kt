package com.iotoms.data.repository

import com.iotoms.data.model.response.AttributeResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.AttributeRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Fasil on 23/11/2025
 */
class AttributeRepositoryImpl(private val client: HttpClient): AttributeRepository {
    override suspend fun getDepartments(): Result<List<AttributeResponse>, ApiError> {
        return apiRequest<List<AttributeResponse>> {
            client.get(ApiUrl.DEPARTMENTS)
        }
    }

    override suspend fun getColors(): Result<List<AttributeResponse>, ApiError> {
        TODO("Not yet implemented")
    }

    override suspend fun getSizes(): Result<List<AttributeResponse>, ApiError> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): Result<List<AttributeResponse>, ApiError> {
        TODO("Not yet implemented")
    }

    override suspend fun getSubCategories(): Result<List<AttributeResponse>, ApiError> {
        TODO("Not yet implemented")
    }

    override suspend fun getBrands(): Result<List<AttributeResponse>, ApiError> {
        TODO("Not yet implemented")
    }

    override suspend fun getStyles(): Result<List<AttributeResponse>, ApiError> {
        TODO("Not yet implemented")
    }
}