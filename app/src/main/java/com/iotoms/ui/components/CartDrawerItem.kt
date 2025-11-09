package com.iotoms.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.PropaneTank
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iotoms.R
import com.iotoms.ui.theme.ButtonHeight
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.PrimaryTeal
import com.iotoms.ui.theme.PrimaryTealLight

/**
 * Created by Fasil on 08/11/2025
 */
fun LazyListScope.cartDrawerItem() {
    item {
        Box(
            modifier = Modifier.fillMaxWidth().height(150.dp).background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PrimaryTeal,
                        PrimaryTealLight
                    )
                )
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.app_name))
        }
    }
    item {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(ButtonHeight)
                .clickable(onClick = { })
                .padding(ExtraSmallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PropaneTank,
                contentDescription = null
            )
            Spacer(Modifier.width(ExtraSmallPadding))
            Text("Products")
        }
    }
    item {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(ButtonHeight)
                .clickable(onClick = { })
                .padding(ExtraSmallPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Groups,
                contentDescription = null
            )
            Spacer(Modifier.width(ExtraSmallPadding))
            Text("Customers")
        }
    }
}