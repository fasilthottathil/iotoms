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
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Discount
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    onClickCartGeneralToggle: () -> Unit
) {
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
                    items(20) {
                        Box(modifier = Modifier.padding(ExtraSmallPadding)) {
                            ProductItem()
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
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(ExtraSmallPadding),
                placeholder = {
                    Text(text = "Search in cart")
                }
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(ExtraSmallPadding)) {
                items(10) {
                    Box(
                        modifier = Modifier.padding(
                            start = ExtraSmallPadding,
                            end = ExtraSmallPadding
                        )
                    ) {
                        CartItem()
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(LargePadding))
                }
            }
        }
    }
}