package com.iotoms.data.repository

import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.mapper.toPlanEntity
import com.iotoms.data.model.response.PlansResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.AccountRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Fasil on 26/12/2025
 */
class AccountRepositoryImpl(
    private val client: HttpClient,
    private val appDatabase: AppDatabase
) : AccountRepository {
    override suspend fun getAllPlans(): Result<PlansResponse, ApiError> {
        return apiRequest<PlansResponse> {
            client.get(ApiUrl.PLANS)
        }.also {
            if (it is Result.Success) {
                val planEntities = it.data.data?.filterNotNull()?.map { planData ->
                    planData.toPlanEntity()
                } ?: emptyList()
                appDatabase.planDao().deleteAllPlans()
                appDatabase.planDao().insertPlans(planEntities)
            }
        }
    }
}