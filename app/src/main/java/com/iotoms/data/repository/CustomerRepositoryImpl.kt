package com.iotoms.data.repository

import com.iotoms.data.model.response.CustomerResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.CustomerRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Fasil on 29/11/2025
 */
class CustomerRepositoryImpl(private val httpClient: HttpClient): CustomerRepository {
    override suspend fun getCustomers(): Result<List<CustomerResponse>, ApiError> {
        return apiRequest<List<CustomerResponse>> {
            httpClient.get(ApiUrl.CUSTOMERS)
        }
    }

    override suspend fun getCustomerById(customerId: String): Result<CustomerResponse, ApiError> {
        return apiRequest<CustomerResponse> {
            httpClient.get(ApiUrl.CUSTOMER_ID.replace("{id}", customerId))
        }
    }

}