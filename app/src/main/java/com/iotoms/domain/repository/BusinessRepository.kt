package com.iotoms.domain.repository

import com.iotoms.data.local.entity.RegisterEntity
import com.iotoms.data.model.response.BrandingResponse
import com.iotoms.data.model.response.RegisterResponse
import com.iotoms.data.model.response.StoreResponse
import com.iotoms.data.model.response.VenueResponseItem
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 26/12/2025
 */
interface BusinessRepository {
    suspend fun getAllVenues(): Result<List<VenueResponseItem>, ApiError>
    suspend fun getAllStores(): Result<List<StoreResponse>, ApiError>
    suspend fun getAllBranding(): Result<List<BrandingResponse>, ApiError>
    suspend fun getAllRegisters(): Result<List<RegisterResponse>, ApiError>
    fun getRegisterInfo(): Flow<RegisterEntity>
}