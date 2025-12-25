package com.iotoms.data.repository

import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.local.entity.DiscountEntity
import com.iotoms.data.model.response.DiscountResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.DiscountRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Fasil on 25/12/2025
 */
class DiscountRepositoryImpl(
    private val client: HttpClient,
    private val appDatabase: AppDatabase
): DiscountRepository {
    override suspend fun getAllDiscounts(): Result<DiscountResponse, ApiError> {
        return apiRequest {
            client.get(ApiUrl.DISCOUNTS)
        }
    }

    override suspend fun calculateDiscount(discountId: Int): Double {
        TODO("Not yet implemented")
    }

    override suspend fun getDiscountById(discountId: Int): DiscountEntity? {
        TODO("Not yet implemented")
    }
}