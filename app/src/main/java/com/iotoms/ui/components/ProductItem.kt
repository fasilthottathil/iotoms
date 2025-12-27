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
import coil3.compose.rememberAsyncImagePainter
import com.iotoms.R
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.NeutralGray800
import com.iotoms.ui.theme.PrimaryTealLight
import com.iotoms.utils.extensions.currencyFormat

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun ProductItem(item: ItemEntity) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .clip(ShapeDefaults.ExtraSmall)
            .background(color = PrimaryTealLight.copy(alpha = 0.3f)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = item.imageGallery?.imageUrl,
                placeholder = painterResource(R.drawable.product_placeholder),
                error = painterResource(R.drawable.product_placeholder)
            ),
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
                text = item.itemName.orEmpty(),
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
                    text = item.sellingPrice.currencyFormat(),
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium,
                    color = NeutralGray50
                )
                Text(
                    text = item.itemId, maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium,
                    color = NeutralGray50
                )
            }
        }
    }
}