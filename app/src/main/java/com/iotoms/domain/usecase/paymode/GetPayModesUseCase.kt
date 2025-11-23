package com.iotoms.domain.usecase.paymode

import com.iotoms.domain.repository.PayModeRepository

/**
 * Created by Fasil on 23/11/2025
 */
class GetPayModesUseCase(private val payModeRepository: PayModeRepository) {
    suspend operator fun invoke() = payModeRepository.getPayModes()
}