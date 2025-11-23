package com.iotoms.domain.repository

import com.iotoms.data.model.response.AttributeResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result

/**
 * Created by Fasil on 23/11/2025
 */
interface AttributeRepository {
    suspend fun getDepartments(): Result<List<AttributeResponse>, ApiError>
    suspend fun getColors(): Result<List<AttributeResponse>, ApiError>
    suspend fun getSizes(): Result<List<AttributeResponse>, ApiError>
    suspend fun getCategories(): Result<List<AttributeResponse>, ApiError>
    suspend fun getSubCategories(): Result<List<AttributeResponse>, ApiError>
    suspend fun getBrands(): Result<List<AttributeResponse>, ApiError>
    suspend fun getStyles(): Result<List<AttributeResponse>, ApiError>
}