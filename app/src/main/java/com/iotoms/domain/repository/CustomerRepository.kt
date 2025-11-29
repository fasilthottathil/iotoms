package com.iotoms.domain.repository

import com.iotoms.data.model.response.CustomerResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result

/**
 * Created by Fasil on 29/11/2025
 */
interface CustomerRepository {
    suspend fun getCustomers(): Result<List<CustomerResponse>, ApiError>
    suspend fun getCustomerById(customerId: String): Result<CustomerResponse, ApiError>
}