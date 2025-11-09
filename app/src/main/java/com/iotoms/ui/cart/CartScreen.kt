package com.iotoms.ui.cart

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.iotoms.data.enum.DeviceOrientation
import com.iotoms.ui.components.cartDrawerItem
import com.iotoms.ui.theme.ButtonHeight
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.LargePadding
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.utils.getDeviceOrientation
import kotlinx.coroutines.launch

/**
 * Created by Fasil on 06/11/2025
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    val orientation = getDeviceOrientation()
    var canShowGeneralCalculator by rememberSaveable { mutableStateOf(false) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                LazyColumn(modifier = Modifier.weight(1f)) {
                   cartDrawerItem()
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
                                Text("RETURN")
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
                                Text("HOLD")
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
                                if (orientation == DeviceOrientation.LANDSCAPE) {
                                    Text("CUSTOMER")
                                }
                            }
                            Spacer(modifier = Modifier.width(LargePadding))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable(onClick = {})
                            ) {
                                Icon(
                                    imageVector = Icons.Default.RemoveShoppingCart,
                                    contentDescription = null
                                )
                                Text("CLEAR")
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Column {
                    if (orientation == DeviceOrientation.PORTRAIT) {
                        CartScreenCompact(
                            modifier = Modifier.padding(innerPadding),
                            canShowGeneralCalculator = canShowGeneralCalculator,
                            onClickCartGeneralToggle = {
                                canShowGeneralCalculator = !canShowGeneralCalculator
                            }
                        )
                    } else {
                        CartScreenExpanded(
                            modifier = Modifier.padding(innerPadding),
                            canShowGeneralCalculator = canShowGeneralCalculator,
                            onClickCartGeneralToggle = {
                                canShowGeneralCalculator = !canShowGeneralCalculator
                            }
                        )
                    }
                }
            }
        }
    )
}

