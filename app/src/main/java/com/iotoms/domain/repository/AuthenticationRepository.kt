package com.iotoms.domain.repository

import com.iotoms.data.model.request.AuthenticateRequest
import com.iotoms.data.model.request.RegistrationRequest
import com.iotoms.data.model.response.AuthenticateResponse
import com.iotoms.data.model.response.RegistrationResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result

/**
 * Created by Fasil on 25/12/2025
 */
interface AuthenticationRepository {
    suspend fun register(registrationRequest: RegistrationRequest): Result<RegistrationResponse, ApiError>
    suspend fun authenticate(authenticateRequest: AuthenticateRequest): Result<AuthenticateResponse, ApiError>
}