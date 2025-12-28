package com.iotoms.domain.usecase.quickpick

import com.iotoms.domain.repository.QuickPickRepository

/**
 * Created by Fasil on 28/12/2025
 */
class GetQuickPickFromDbUseCase(private val quickPickRepository: QuickPickRepository) {
    operator fun invoke() = quickPickRepository.getQuickPicksFromDb()
}