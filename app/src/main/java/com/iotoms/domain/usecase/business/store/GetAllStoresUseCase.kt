package com.iotoms.domain.usecase.business.store

import com.iotoms.domain.repository.BusinessRepository

/**
 * Created by Fasil on 27/12/2025
 */
class GetAllStoresUseCase(private val businessRepository: BusinessRepository) {
    suspend operator fun invoke() = businessRepository.getAllStores()
}