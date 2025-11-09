package com.iotoms.ui.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iotoms.ui.components.CustomerItem
import com.iotoms.ui.components.OutlinedTextBox
import com.iotoms.ui.theme.ExtraSmallPadding

/**
 * Created by Fasil on 08/11/2025
 */
@Composable
fun CustomersScreenCompact(modifier: Modifier) {
    Column(modifier = modifier) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(ExtraSmallPadding)
        ) {
            OutlinedTextBox(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = { },
                placeholder = {
                    Text("Search customers")
                }
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp)
        ) {
            items(10) {
                Box(Modifier.padding(ExtraSmallPadding)) {
                    CustomerItem()
                }
            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}