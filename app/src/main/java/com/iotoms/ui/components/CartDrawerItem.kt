package com.iotoms.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.iotoms.R
import com.iotoms.ui.cart.RegisterInfo
import com.iotoms.ui.theme.ButtonHeight
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.PrimaryTeal
import com.iotoms.ui.theme.PrimaryTealLight

/**
 * Created by Fasil on 08/11/2025
 */
fun LazyListScope.cartDrawerItem(regInfo: RegisterInfo?) {
    item {
        Column(
            modifier = Modifier.fillMaxWidth().heightIn(min = 150.dp).background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PrimaryTeal,
                        PrimaryTealLight
                    )
                )
            ).padding(ExtraSmallPadding)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.iotoms_logo),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
                Text(stringResource(R.string.app_name), color = NeutralGray50)
            }
            Spacer(Modifier.height(MediumPadding))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Venue: ", fontWeight = FontWeight.Bold, color = NeutralGray50)
                Text("${regInfo?.venueName}", color = NeutralGray50)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Store: ", fontWeight = FontWeight.Bold, color = NeutralGray50)
                Text("${regInfo?.storeName}", color = NeutralGray50)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Register: ", fontWeight = FontWeight.Bold, color = NeutralGray50)
                Text("${regInfo?.registerName} - (${regInfo?.id})", color = NeutralGray50)
            }
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