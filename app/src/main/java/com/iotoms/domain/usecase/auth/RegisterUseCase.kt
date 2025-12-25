package com.iotoms.domain.usecase.auth

import com.iotoms.data.model.request.RegistrationRequest
import com.iotoms.domain.repository.AuthenticationRepository

/**
 * Created by Fasil on 25/12/2025
 */
class RegisterUseCase(private val authenticationRepository: AuthenticationRepository) {
    suspend operator fun invoke(registrationRequest: RegistrationRequest) =
        authenticationRepository.register(registrationRequest)
}