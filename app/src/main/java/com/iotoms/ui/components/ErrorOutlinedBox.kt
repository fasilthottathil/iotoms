package com.iotoms.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.iotoms.ui.theme.ErrorRed
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.SmallPadding

/**
 * Created by Fasil on 25/12/2025
 */
@Composable
fun ErrorOutlinedBox(modifier: Modifier = Modifier, error: String, onDismiss: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(ErrorRed.copy(alpha = 0.4f), shape = ShapeDefaults.Small),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MediumPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(error, color = NeutralGray50, modifier = Modifier.weight(1f))
                Spacer(Modifier.width(SmallPadding))
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.clickable(onClick = onDismiss)
                )
            }
        }
    )
}