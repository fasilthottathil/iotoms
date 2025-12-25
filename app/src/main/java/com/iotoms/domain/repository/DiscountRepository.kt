package com.iotoms.domain.repository

import com.iotoms.data.local.entity.DiscountEntity
import com.iotoms.data.model.response.DiscountResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result

/**
 * Created by Fasil on 20/12/2025
 */
interface DiscountRepository {
    suspend fun getAllDiscounts(): Result<DiscountResponse, ApiError>
    suspend fun calculateDiscount(discountId: Int): Double
    suspend fun getDiscountById(discountId: Int): DiscountEntity?
}