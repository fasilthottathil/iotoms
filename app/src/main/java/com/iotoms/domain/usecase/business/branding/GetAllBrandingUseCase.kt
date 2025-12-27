package com.iotoms.domain.usecase.business.branding

import com.iotoms.domain.repository.BusinessRepository

/**
 * Created by Fasil on 27/12/2025
 */
class GetAllBrandingUseCase(private val businessRepository: BusinessRepository) {
    suspend operator fun invoke() = businessRepository.getAllBranding()
}