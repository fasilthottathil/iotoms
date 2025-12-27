package com.iotoms.domain.usecase.plan

import com.iotoms.domain.repository.AccountRepository

/**
 * Created by Fasil on 26/12/2025
 */
class GetAllPlansUseCase(private val accountRepository: AccountRepository) {
    suspend operator fun invoke() = accountRepository.getAllPlans()
}