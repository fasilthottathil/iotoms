package com.iotoms.domain.usecase.business.register

import com.iotoms.domain.repository.BusinessRepository

/**
 * Created by Fasil on 28/12/2025
 */
class GetRegisterInfoUseCase(private val businessRepository: BusinessRepository) {
    operator fun invoke() = businessRepository.getRegisterInfo()
}