package com.iotoms.data.repository

import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.mapper.toUserEntity
import com.iotoms.data.model.response.UserResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.UserRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Fasil on 25/12/2025
 */
class UserRepositoryImpl(
    private val client: HttpClient,
    private val appDatabase: AppDatabase
) : UserRepository {
    override suspend fun getAllUsers(): Result<UserResponse, ApiError> {
        return apiRequest<UserResponse> {
            client.get(ApiUrl.USERS)
        }.also {
            if (it is Result.Success) {
                it.data.data?.filterNotNull()?.map { item ->
                    item.toUserEntity()
                }?.also { userEntities ->
                    appDatabase.userDao().insertUsers(userEntities)
                }
            }
        }
    }
}