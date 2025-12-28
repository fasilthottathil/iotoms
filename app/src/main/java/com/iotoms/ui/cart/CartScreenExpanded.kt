package com.iotoms.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.ui.components.CartItem
import com.iotoms.ui.components.OutlinedTextBox
import com.iotoms.ui.components.ProductItem
import com.iotoms.ui.components.QuickPickItem
import com.iotoms.ui.theme.ButtonHeight
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.LargePadding
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.NeutralGray700
import com.iotoms.ui.theme.PrimaryTealLight
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.ui.theme.SuccessGreen

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun CartScreenExpanded(
    modifier: Modifier,
    canShowGeneralCalculator: Boolean = false,
    onClickCartGeneralToggle: () -> Unit,
    pagingItems: LazyPagingItems<ItemEntity>,
    onItemClick: (ItemEntity) -> Unit,
    onGeneralItemClick: (String) -> Unit,
    uiState: State<CartUiState>,
    onUpdateQuantity: (CartItemEntity) -> Unit,
    setQuickPickItemSource: (ItemSource) -> Unit,
    itemSource: State<ItemSource>
) {
    val focusManager = LocalFocusManager.current
    var searchQuery by rememberSaveable { mutableStateOf("") }

    val cartItems by remember(searchQuery, uiState.value.cart.cartItems) {
        derivedStateOf {
            val items = uiState.value.cart.cartItems
            if (searchQuery.isBlank()) {
                items
            } else {
                items.filter { item ->
                    item.itemId.contains(searchQuery, ignoreCase = true) ||
                            item.name.contains(searchQuery, ignoreCase = true)
                }
            }
        }
    }
    val quickPicks = uiState.value.cart.quickPicks
    Row(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.7f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ButtonHeight)
                    .background(color = NeutralGray700)
                    .padding(SmallPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Dialpad,
                    contentDescription = null,
                    tint = if (canShowGeneralCalculator) SuccessGreen else NeutralGray50,
                    modifier = Modifier.clickable(onClick = onClickCartGeneralToggle)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Outlined.Discount,
                    contentDescription = null,
                    tint = NeutralGray50
                )
                Spacer(Modifier.width(MediumPadding))
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = NeutralGray50
                )
            }
            Spacer(modifier = Modifier.height(SmallPadding))
            if (canShowGeneralCalculator) {
                GeneralItemCalculatorScreen(onClickAdd = onGeneralItemClick, uiState = uiState)
            } else {
                if (quickPicks.isNotEmpty()) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(SmallPadding)) {
                        item {  }
                        item {
                            QuickPickItem(
                                quickPick = QuickPick(
                                    id = "All Items",
                                    label = "All Items",
                                    backgroundColor = null,
                                    itemIds = listOf()
                                ),
                                isSelected = itemSource.value is ItemSource.All,
                                onClick = {
                                    setQuickPickItemSource(ItemSource.All)
                                }
                            )
                        }
                        items(
                            count = quickPicks.size,
                            key = { index -> quickPicks[index].id }
                        ) {
                            QuickPickItem(
                                quickPick = quickPicks[it],
                                isSelected = if (itemSource.value is ItemSource.ByIds) {
                                    (itemSource.value as ItemSource.ByIds).pageId == quickPicks[it].id
                                } else {
                                    false
                                },
                                onClick = {
                                    setQuickPickItemSource(
                                        ItemSource.ByIds(
                                            itemIds = quickPicks[it].itemIds,
                                            pageId = quickPicks[it].id
                                        )
                                    )
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(ExtraSmallPadding))
                }
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 200.dp)
                ) {
                    when (pagingItems.loadState.refresh) {
                        is LoadState.Loading -> {
                            item { CircularProgressIndicator() }
                        }

                        else -> {
                            items(
                                count = pagingItems.itemCount,
                                key = { index -> pagingItems[index]?.id ?: index }
                            ) { index ->
                                pagingItems[index]?.let { item ->
                                    Box(
                                        modifier = Modifier
                                            .padding(ExtraSmallPadding)
                                            .clickable(onClick = { onItemClick(item) })
                                    ) {
                                        ProductItem(item)
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = PrimaryTealLight.copy(alpha = 0.1f))
                .weight(0.4f)
        ) {
            OutlinedTextBox(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(ExtraSmallPadding)
                ,
                placeholder = {
                    Text(text = "Search in cart")
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.clickable(onClick = {
                                searchQuery = ""
                                focusManager.clearFocus()
                            })
                        )
                    }
                }
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(ExtraSmallPadding)) {
                items(
                    count = cartItems.size,
                    key = { index -> cartItems[index].id }
                ) { index ->
                    Box(
                        modifier = Modifier.padding(
                            start = ExtraSmallPadding,
                            end = ExtraSmallPadding
                        )
                    ) {
                        CartItem(cartItem = cartItems[index], onUpdateQuantity = onUpdateQuantity)
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(LargePadding))
                }
            }
        }
    }
}