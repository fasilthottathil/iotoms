package com.iotoms.di

import com.iotoms.ui.auth.login.LoginViewModel
import com.iotoms.ui.cart.CartViewModel
import com.iotoms.ui.item.search.SearchItemViewModel
import com.iotoms.ui.item.view.ViewItemViewModel
import com.iotoms.ui.sync.DataSyncViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Fasil on 22/11/2025
 */
val viewModelModule = module {
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<DataSyncViewModel> { DataSyncViewModel(get()) }
    viewModel<CartViewModel> { CartViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    viewModel<SearchItemViewModel> { SearchItemViewModel(get(),get(),get()) }
    viewModel<ViewItemViewModel> { ViewItemViewModel(get(), get()) }
}