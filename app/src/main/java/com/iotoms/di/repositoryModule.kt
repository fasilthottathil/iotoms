package com.iotoms.di

import com.iotoms.data.repository.AccountRepositoryImpl
import com.iotoms.data.repository.AttributeRepositoryImpl
import com.iotoms.data.repository.AuthenticationRepositoryImpl
import com.iotoms.data.repository.BusinessRepositoryImpl
import com.iotoms.data.repository.CustomerRepositoryImpl
import com.iotoms.data.repository.DataSyncRepositoryImpl
import com.iotoms.data.repository.ItemRepositoryImpl
import com.iotoms.data.repository.PayModeRepositoryImpl
import com.iotoms.data.repository.TaxRepositoryImpl
import com.iotoms.data.repository.UserRepositoryImpl
import com.iotoms.domain.repository.AccountRepository
import com.iotoms.domain.repository.AttributeRepository
import com.iotoms.domain.repository.AuthenticationRepository
import com.iotoms.domain.repository.BusinessRepository
import com.iotoms.domain.repository.CustomerRepository
import com.iotoms.domain.repository.DataSyncRepository
import com.iotoms.domain.repository.ItemRepository
import com.iotoms.domain.repository.PayModeRepository
import com.iotoms.domain.repository.TaxRepository
import com.iotoms.domain.repository.UserRepository
import org.koin.dsl.module

/**
 * Created by Fasil on 26/10/2025
 */
val repositoryModule = module {
    factory<PayModeRepository> { PayModeRepositoryImpl(get()) }
    factory<AuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }
    factory<ItemRepository> { ItemRepositoryImpl(get(), get()) }
    factory<DataSyncRepository> { DataSyncRepositoryImpl(get(), get(), get(), get(), get(), get(), get()) }
    factory<TaxRepository> { TaxRepositoryImpl(get(), get()) }
    factory<AttributeRepository> { AttributeRepositoryImpl(get(), get()) }
    factory<CustomerRepository> { CustomerRepositoryImpl(get(), get()) }
    factory<PayModeRepository> { PayModeRepositoryImpl(get()) }
    factory<UserRepository> { UserRepositoryImpl(get(), get()) }
    factory<AccountRepository> { AccountRepositoryImpl(get(), get()) }
    factory<BusinessRepository> { BusinessRepositoryImpl(get(), get()) }
}