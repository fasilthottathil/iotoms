package com.iotoms.data.repository

import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.mapper.toBrandingEntity
import com.iotoms.data.mapper.toRegisterEntity
import com.iotoms.data.mapper.toStoreEntity
import com.iotoms.data.mapper.toVenueEntity
import com.iotoms.data.model.response.BrandingResponse
import com.iotoms.data.model.response.RegisterResponse
import com.iotoms.data.model.response.StoreResponse
import com.iotoms.data.model.response.VenueResponseItem
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.BusinessRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Fasil on 27/12/2025
 */
class BusinessRepositoryImpl(
    private val client: HttpClient,
    private val appDatabase: AppDatabase
) : BusinessRepository {
    override suspend fun getAllVenues(): Result<List<VenueResponseItem>, ApiError> {
        return apiRequest<List<VenueResponseItem>> {
            client.get(ApiUrl.VENUES)
        }.also {
            if (it is Result.Success) {
                it.data.map { item -> item.toVenueEntity() }
                    .also { venueEntities ->
                        appDatabase.venueDao().deleteAllVenues()
                        appDatabase.venueDao().insertVenues(venueEntities)
                    }
            }
        }
    }

    override suspend fun getAllStores(): Result<List<StoreResponse>, ApiError> {
        return apiRequest<List<StoreResponse>> {
            client.get(ApiUrl.STORES)
        }.also {
            if (it is Result.Success) {
                it.data.map { item -> item.toStoreEntity() }
                    .also { storeEntities ->
                        appDatabase.storeDao().deleteAllStores()
                        appDatabase.storeDao().insertStores(storeEntities)
                    }
            }
        }
    }

    override suspend fun getAllBranding(): Result<List<BrandingResponse>, ApiError> {
        return apiRequest<List<BrandingResponse>> {
            client.get(ApiUrl.STORE_BRANDING)
        }.also {
            if (it is Result.Success) {
                it.data.map { item -> item.toBrandingEntity() }
                    .also { brandingEntities ->
                        appDatabase.brandingDao().deleteAllBranding()
                        appDatabase.brandingDao().insertBranding(brandingEntities)
                    }
            }
        }
    }

    override suspend fun getAllRegisters(): Result<List<RegisterResponse>, ApiError> {
        return apiRequest<List<RegisterResponse>> {
            client.get(ApiUrl.REGISTERS)
        }.also {
            if (it is Result.Success) {
                it.data.map { item -> item.toRegisterEntity() }.also { registerEntities ->
                    appDatabase.registerDao().deleteAllRegisters()
                    appDatabase.registerDao().insertRegisters(registerEntities)
                }
            }
        }
    }
}