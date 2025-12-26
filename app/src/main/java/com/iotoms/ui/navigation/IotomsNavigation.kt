package com.iotoms.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.iotoms.ui.auth.login.Login
import com.iotoms.ui.auth.login.LoginScreen
import com.iotoms.ui.auth.login.LoginViewModel
import com.iotoms.ui.cart.Cart
import com.iotoms.ui.cart.CartScreen
import com.iotoms.ui.sync.DataSync
import com.iotoms.ui.sync.DataSyncDialogScreen
import com.iotoms.ui.sync.DataSyncViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by Fasil on 22/11/2025
 */
@Composable
fun IotomsNavigation() {
    val backStack = rememberNavBackStack(Login)
    NavDisplay(
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        entryProvider = entryProvider {
            entry<Login> {
                val viewModel = koinViewModel<LoginViewModel>()
                LoginScreen(
                    state = viewModel.uiState.collectAsStateWithLifecycle(),
                    onLoginClick = {
                        viewModel.login(it)
                    },
                    onLogin = {
                        backStack.removeLastOrNull()
                        backStack.add(DataSync)
                    }
                )
            }
            entry<DataSync> {
                val viewModel = koinViewModel<DataSyncViewModel>()
                DataSyncDialogScreen(
                    uiState = viewModel.uiState.collectAsStateWithLifecycle(),
                    onDismiss = {
                        backStack.removeLastOrNull()
                    },
                    onSync = {
                        backStack.add(Cart)
                    }
                )
            }
            entry<Cart> {
                CartScreen()
            }
        }
    )
}