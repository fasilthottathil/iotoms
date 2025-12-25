package com.iotoms.data.repository

import com.iotoms.data.local.pref.AppPreference
import com.iotoms.data.model.request.AuthenticateRequest
import com.iotoms.data.model.request.RegistrationRequest
import com.iotoms.data.model.response.AuthenticateResponse
import com.iotoms.data.model.response.RegistrationResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.AuthenticationRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody

/**
 * Created by Fasil on 25/12/2025
 */
class AuthenticationRepositoryImpl(
    private val client: HttpClient,
    private val pref: AppPreference
) : AuthenticationRepository {
    override suspend fun register(registrationRequest: RegistrationRequest): Result<RegistrationResponse, ApiError> {
        return apiRequest<RegistrationResponse> {
            client.post(ApiUrl.REGISTER) {
                setBody(registrationRequest)
                header("X-TenantID", registrationRequest.merchantTenant)
            }
        }.also {
            if (it is Result.Success) {
                pref.setBearerToken(it.data.data?.token.orEmpty())
                pref.setDomainName(it.data.data?.tenant.orEmpty())
            }
        }
    }

    override suspend fun authenticate(authenticateRequest: AuthenticateRequest): Result<AuthenticateResponse, ApiError> {
        return apiRequest {
            client.post(ApiUrl.AUTHENTICATE) {
                setBody(authenticateRequest)
            }
        }
    }
}