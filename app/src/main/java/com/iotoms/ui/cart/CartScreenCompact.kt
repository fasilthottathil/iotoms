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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.ui.components.ProductItem
import com.iotoms.ui.components.QuickPickItem
import com.iotoms.ui.theme.AppBarHeight
import com.iotoms.ui.theme.ButtonHeight
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.NeutralGray700
import com.iotoms.ui.theme.PrimaryTealLight
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.ui.theme.SuccessGreen
import com.iotoms.utils.extensions.currencyFormat

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun CartScreenCompact(
    modifier: Modifier,
    canShowGeneralCalculator: Boolean,
    onClickCartGeneralToggle: () -> Unit,
    pagingItems: LazyPagingItems<ItemEntity>,
    onItemClick: (ItemEntity) -> Unit,
    onGeneralItemClick: (String) -> Unit,
    uiState: State<CartUiState>,
    setQuickPickItemSource: (ItemSource) -> Unit,
    itemSource: State<ItemSource>
) {
    val quickPicks = uiState.value.cart.quickPicks
    val isEmptyCart = uiState.value.cart.cartItems.isEmpty()
    Column(modifier = modifier.fillMaxSize()) {
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
            Box(Modifier.weight(1f)) {
                GeneralItemCalculatorScreen(onClickAdd = onGeneralItemClick, uiState = uiState)
            }
        } else {
            if (quickPicks.isNotEmpty()) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(SmallPadding)) {
                    item { }
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
                columns = GridCells.Adaptive(minSize = 200.dp),
                modifier = Modifier.weight(1f)
            ) {
                when (pagingItems.loadState.refresh) {
                    is LoadState.Loading -> {
                        item { CircularProgressIndicator(modifier = Modifier.size(40.dp)) }
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
        if (isEmptyCart.not()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = AppBarHeight)
                    .background(color = PrimaryTealLight)
                    .clickable(onClick = {})
                    .padding(SmallPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Items: ${uiState.value.cart.cartItems.sumOf { it.quantity }.toInt()}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = NeutralGray50
                    )
                    Text(
                        text = "Total: ${uiState.value.cart.cartItems.sumOf { it.total }.currencyFormat()}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = NeutralGray50
                    )
                }
                Spacer(Modifier.weight(1f))
                Text(
                    text = "Checkout",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = NeutralGray50
                )
                Spacer(Modifier.width(ExtraSmallPadding))
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                    contentDescription = null,
                    tint = NeutralGray50
                )
            }
        }
    }
}