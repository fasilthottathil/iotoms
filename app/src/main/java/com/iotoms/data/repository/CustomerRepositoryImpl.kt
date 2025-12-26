package com.iotoms.data.repository

import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.mapper.toCustomerEntity
import com.iotoms.data.model.response.CustomerResponse
import com.iotoms.data.model.response.PageResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.CustomerRepository
import com.iotoms.utils.NetworkError
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Fasil on 29/11/2025
 */
class CustomerRepositoryImpl(private val client: HttpClient, private val appDatabase: AppDatabase): CustomerRepository {
    override suspend fun getCustomers(page: Int): Result<List<Boolean>, ApiError> {
        val pageStates = mutableListOf<Boolean>()
        var currentPage = page

        while (true) {

            val result = apiRequest<PageResponse<CustomerResponse>> {
                client.get(ApiUrl.CUSTOMERS.plus("?page=$currentPage"))
            }

            when (result) {
                is Result.Success -> {
                    val customers = result.data.content?.map { it.toCustomerEntity() } ?: emptyList()
                    if (customers.isEmpty()) {
                        return Result.Success(pageStates)
                    }
                    appDatabase.customerDao().insertCustomers(customers)
                    pageStates += true
                    currentPage++        // move to next page
                }

                is Result.Error -> {
                    // stop only on 404 (no more pages)
                    if (result.error.networkError == NetworkError.NOT_FOUND) {
                        return Result.Success(pageStates)
                    }

                    // for any other error → fail immediately
                    return Result.Error(result.error)
                }
            }
        }
    }

    override suspend fun getCustomerById(customerId: String): Result<CustomerResponse, ApiError> {
        return apiRequest<CustomerResponse> {
            client.get(ApiUrl.CUSTOMER_ID.replace("{id}", customerId))
        }
    }

}