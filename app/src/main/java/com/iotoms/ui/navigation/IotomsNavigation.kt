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
                    }
                )
            }
        }
    )
}