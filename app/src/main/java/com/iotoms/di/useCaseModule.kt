package com.iotoms.di

import com.iotoms.domain.usecase.paymode.GetPayModesUseCase
import org.koin.dsl.module

/**
 * Created by Fasil on 26/10/2025
 */
val useCaseModule = module {
    factory { GetPayModesUseCase(get()) }
}