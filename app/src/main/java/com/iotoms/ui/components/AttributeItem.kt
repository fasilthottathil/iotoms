package com.iotoms.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.iotoms.ui.theme.SmallPadding

/**
 * Created by Fasil on 03/01/2026
 */
@Composable
fun AttributeItem(
    attr: Pair<Int, String>,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(OutlinedTextFieldDefaults.MinHeight)
            .clickable(onClick = onClick)
            .padding(SmallPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(attr.second, modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.AutoMirrored.Default.ArrowForward, contentDescription = null)
    }
}