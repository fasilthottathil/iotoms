package com.iotoms.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.iotoms.R
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.NeutralGray800
import com.iotoms.ui.theme.PrimaryTealLight

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun ProductItem() {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .clip(ShapeDefaults.ExtraSmall)
            .background(color = PrimaryTealLight.copy(alpha = 0.3f)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(NeutralGray800.copy(alpha = 0.8f))
                .padding(ExtraSmallPadding)
        ) {
            Text(
                text = "Product Name",
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge,
                color = NeutralGray50
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$99.99",
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium,
                    color = NeutralGray50
                )
                Text(
                    text = "Item id", maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium,
                    color = NeutralGray50
                )
            }
        }
    }
}