package com.iotoms.di

import com.iotoms.ui.auth.login.LoginViewModel
import com.iotoms.ui.sync.DataSyncViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Fasil on 22/11/2025
 */
val viewModelModule = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<DataSyncViewModel> { DataSyncViewModel(get()) }
}