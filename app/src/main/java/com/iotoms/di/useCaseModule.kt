package com.iotoms.di

import com.iotoms.domain.usecase.auth.RegisterUseCase
import com.iotoms.domain.usecase.paymode.GetPayModesUseCase
import com.iotoms.domain.usecase.plan.GetAllPlansUseCase
import com.iotoms.domain.usecase.sync.DataSyncUseCase
import org.koin.dsl.module

/**
 * Created by Fasil on 26/10/2025
 */
val useCaseModule = module {
    factory { GetPayModesUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { DataSyncUseCase(get()) }
    factory { GetAllPlansUseCase(get()) }
}