package com.iotoms.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.iotoms.R
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.PrimaryTealDark
import com.iotoms.ui.theme.SmallPadding

/**
 * Created by Fasil on 07/11/2025
 */
@Composable
fun CartItem() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(ExtraSmallPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(ShapeDefaults.Medium)
            )
            Spacer(Modifier.width(ExtraSmallPadding))
            Column {
                Text(
                    "Item name",
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(ExtraSmallPadding))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "item id",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Text(
                        "$99.99",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(ShapeDefaults.Medium)
                            .background(color = PrimaryTealDark)
                            .clickable(onClick = {}),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "-",
                            color = NeutralGray50,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    Spacer(modifier = Modifier.width(SmallPadding))
                    Text(
                        "1",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(SmallPadding))
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(ShapeDefaults.Medium)
                            .background(color = PrimaryTealDark)
                            .clickable(onClick = {}),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "+",
                            color = NeutralGray50,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.width(SmallPadding))
                }
                Spacer(Modifier.height(ExtraSmallPadding))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("D: 0.00", style = MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.weight(1f))
                    Text(
                        "$10.00",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.width(SmallPadding))
                }
            }
        }
    }
}