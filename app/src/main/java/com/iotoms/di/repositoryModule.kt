package com.iotoms.di

import com.iotoms.data.repository.PayModeRepositoryImpl
import com.iotoms.domain.repository.PayModeRepository
import org.koin.dsl.module

/**
 * Created by Fasil on 26/10/2025
 */
val repositoryModule = module {
    factory<PayModeRepository> { PayModeRepositoryImpl(get()) }
}