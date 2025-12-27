package com.iotoms.domain.usecase.business.venue

import com.iotoms.domain.repository.BusinessRepository

/**
 * Created by Fasil on 27/12/2025
 */
class GetAllVenueUseCase(private val businessRepository: BusinessRepository) {
    suspend operator fun invoke() = businessRepository.getAllVenues()
}