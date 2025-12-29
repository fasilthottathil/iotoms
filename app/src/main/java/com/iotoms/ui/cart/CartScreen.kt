package com.iotoms.ui.cart

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.PauseCircleOutline
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.NavKey
import androidx.paging.compose.LazyPagingItems
import com.iotoms.data.enum.DeviceOrientation
import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.ui.components.ConfirmationDialog
import com.iotoms.ui.components.cartDrawerItem
import com.iotoms.ui.theme.ButtonHeight
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.LargePadding
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.utils.getDeviceOrientation
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 06/11/2025
 */
@Serializable
data object Cart: NavKey
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    uiState: State<CartUiState>,
    pagingItems: LazyPagingItems<ItemEntity>,
    onItemClick: (ItemEntity) -> Unit = {},
    onGeneralItemClick: (String) -> Unit = {},
    onUpdateQuantity: (CartItemEntity) -> Unit = {},
    onClearCart: () -> Unit = {},
    itemSource: State<ItemSource>,
    setQuickPickItemSource: (ItemSource) -> Unit = {},
    onClickSearch: () -> Unit = {}
) {
    val context = LocalContext.current
    val orientation = getDeviceOrientation()
    var canShowGeneralCalculator by rememberSaveable { mutableStateOf(false) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showClearCartConfirmDialog by rememberSaveable { mutableStateOf(false) }
    val cartItems = uiState.value.cart.cartItems
    val regInfo = uiState.value.cart.regInfo

    if (showClearCartConfirmDialog) {
        ConfirmationDialog(
            title = "Clear Cart",
            message = "Are you sure you want to clear the cart?",
            onYes = {
                onClearCart()
                showClearCartConfirmDialog = false
            },
            onNo = {
                showClearCartConfirmDialog = false
            },
            onDismiss = {
                showClearCartConfirmDialog = false
            }
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                LazyColumn(modifier = Modifier.weight(1f)) {
                   cartDrawerItem(regInfo)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ButtonHeight)
                        .clickable(onClick = { })
                        .padding(ExtraSmallPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(ExtraSmallPadding))
                    Text("Configurations")
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Dashboard,
                                    contentDescription = null,
                                    modifier = Modifier.clickable(onClick = {
                                        scope.launch {
                                            if (drawerState.isOpen) drawerState.close() else drawerState.open()
                                        }
                                    })
                                )
                                if (orientation == DeviceOrientation.LANDSCAPE) {
                                    Spacer(Modifier.width(SmallPadding))
                                    Text("Transaction Cart")
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        actions = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable(onClick = {})
                            ) {
                                Icon(
                                    imageVector = Icons.Default.SwapHoriz,
                                    contentDescription = null
                                )
                            }
                            Spacer(modifier = Modifier.width(LargePadding))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable(onClick = {})
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PauseCircleOutline,
                                    contentDescription = null
                                )
                            }
                            Spacer(modifier = Modifier.width(LargePadding))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable(onClick = {})
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PersonAddAlt,
                                    contentDescription = null
                                )
                            }
                            Spacer(modifier = Modifier.width(LargePadding))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable(onClick = {
                                    if (cartItems.isNotEmpty()) {
                                        showClearCartConfirmDialog = true
                                    } else {
                                        Toast.makeText(context, "Cart is already empty", Toast.LENGTH_SHORT).show()
                                    }
                                })
                            ) {
                                Icon(
                                    imageVector = Icons.Default.RemoveShoppingCart,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Column {
                    if (orientation == DeviceOrientation.PORTRAIT) {
                        CartScreenCompact(
                            modifier = Modifier.padding(innerPadding),
                            pagingItems = pagingItems,
                            canShowGeneralCalculator = canShowGeneralCalculator,
                            onClickCartGeneralToggle = {
                                canShowGeneralCalculator = !canShowGeneralCalculator
                            },
                            onItemClick = onItemClick,
                            onGeneralItemClick = onGeneralItemClick,
                            uiState = uiState,
                            setQuickPickItemSource = setQuickPickItemSource,
                            itemSource = itemSource,
                            onClickSearch = onClickSearch
                        )
                    } else {
                        CartScreenExpanded(
                            modifier = Modifier.padding(innerPadding),
                            pagingItems = pagingItems,
                            canShowGeneralCalculator = canShowGeneralCalculator,
                            onClickCartGeneralToggle = {
                                canShowGeneralCalculator = !canShowGeneralCalculator
                            },
                            onItemClick = onItemClick,
                            uiState = uiState,
                            onUpdateQuantity = onUpdateQuantity,
                            onGeneralItemClick = onGeneralItemClick,
                            setQuickPickItemSource = setQuickPickItemSource,
                            itemSource = itemSource,
                            onClickSearch = onClickSearch
                        )
                    }
                }
            }
        }
    )
}

