package com.iotoms.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.paging.compose.collectAsLazyPagingItems
import com.iotoms.data.local.pref.AppPreference
import com.iotoms.ui.auth.login.Login
import com.iotoms.ui.auth.login.LoginScreen
import com.iotoms.ui.auth.login.LoginViewModel
import com.iotoms.ui.cart.Cart
import com.iotoms.ui.cart.CartScreen
import com.iotoms.ui.cart.CartViewModel
import com.iotoms.ui.item.add.AddItemScreen
import com.iotoms.ui.item.add.AddItemScreenNavKey
import com.iotoms.ui.item.search.SearchItemScreen
import com.iotoms.ui.item.search.SearchItemScreenNavKey
import com.iotoms.ui.item.search.SearchItemViewModel
import com.iotoms.ui.item.view.ViewItemScreen
import com.iotoms.ui.item.view.ViewItemScreenNavKey
import com.iotoms.ui.item.view.ViewItemViewModel
import com.iotoms.ui.sync.DataSync
import com.iotoms.ui.sync.DataSyncDialogScreen
import com.iotoms.ui.sync.DataSyncViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by Fasil on 22/11/2025
 */
@Composable
fun IotomsNavigation() {
    val appPreference = koinInject<AppPreference>()
    val backStack =
        rememberNavBackStack(if (appPreference.getDomainName().isNullOrEmpty()) Login else Cart)
    NavDisplay(
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = backStack,
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith // Previous screen enters from left
                    slideOutHorizontally(targetOffsetX = { it })
        },
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
                val viewModel = koinViewModel<CartViewModel>()
                CartScreen(
                    uiState = viewModel.uiState.collectAsStateWithLifecycle(),
                    pagingItems = viewModel.pagingItemsFlow.collectAsLazyPagingItems(),
                    itemSource = viewModel.itemSource.collectAsStateWithLifecycle(),
                    onItemClick = { viewModel.addItemToCart(it) },
                    onUpdateQuantity = viewModel::updateQuantity,
                    onGeneralItemClick = viewModel::addGeneralItemToCart,
                    onClearCart = viewModel::clearCart,
                    setQuickPickItemSource = viewModel::setItemSource,
                    onClickSearch = {
                        backStack.add(SearchItemScreenNavKey())
                    },
                    backStack = backStack
                )
            }
            entry<SearchItemScreenNavKey> {
                val viewModel = koinViewModel<SearchItemViewModel>()
                SearchItemScreen(
                    uiState = viewModel.uiState.collectAsStateWithLifecycle(),
                    pagingItems = viewModel.pagingItemsFlow.collectAsLazyPagingItems(),
                    onSearch = viewModel::onSearch,
                    onItemClick = viewModel::addItemToCart,
                    onClickBack = {
                        backStack.removeLastOrNull()
                    },
                    onViewItem = {
                        backStack.add(ViewItemScreenNavKey(it.itemId))
                    },
                    isSearch = it.isSearch,
                    onClickItemAdd = {
                        backStack.add(AddItemScreenNavKey)
                    }
                )
            }
            entry<ViewItemScreenNavKey> {
                val viewModel = koinViewModel<ViewItemViewModel>()
                LaunchedEffect(it.itemId) {
                    viewModel.getItemByItemId(it.itemId)
                }
                ViewItemScreen(
                    uiState = viewModel.uiState.collectAsStateWithLifecycle(),
                    onClickBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
            entry<AddItemScreenNavKey> {
                AddItemScreen(
                    onClickBack = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        }
    )
}