package com.iotoms.domain.usecase.business.register

import com.iotoms.domain.repository.BusinessRepository

/**
 * Created by Fasil on 27/12/2025
 */
class GetAllRegisterUseCase(private val businessRepository: BusinessRepository) {
    suspend operator fun invoke() = businessRepository.getAllRegisters()
}