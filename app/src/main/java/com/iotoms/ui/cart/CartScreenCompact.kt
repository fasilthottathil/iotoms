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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.ui.components.ProductItem
import com.iotoms.ui.components.QuickPickItem
import com.iotoms.ui.theme.ButtonHeight
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.NeutralGray700
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.ui.theme.SuccessGreen

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun CartScreenCompact(
    modifier: Modifier,
    canShowGeneralCalculator: Boolean,
    onClickCartGeneralToggle: () -> Unit,
    pagingItems: LazyPagingItems<ItemEntity>
) {
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
            GeneralItemCalculatorScreen(onClickAdd = { })
        } else {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(SmallPadding)) {
                items(10) {
                    QuickPickItem()
                }
            }
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
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
                                Box(modifier = Modifier.padding(ExtraSmallPadding)) {
                                    ProductItem(item)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}