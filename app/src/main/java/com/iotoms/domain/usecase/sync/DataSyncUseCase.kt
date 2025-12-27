package com.iotoms.domain.usecase.sync

import com.iotoms.domain.repository.DataSyncRepository

/**
 * Created by Fasil on 26/12/2025
 */
class DataSyncUseCase(private val syncRepository: DataSyncRepository) {
    suspend operator fun invoke() = syncRepository.doSync()
}