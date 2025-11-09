package com.iotoms.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iotoms.ui.theme.ButtonHeight
import com.iotoms.ui.theme.PrimaryTeal

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun QuickPickItem() {
    ElevatedAssistChip(
        modifier = Modifier.height(ButtonHeight),
        onClick = { /*TODO*/ },
        label = {
            Text("Quick pick item")
        },
        border = BorderStroke(1.dp, color = PrimaryTeal),
        colors = AssistChipDefaults.elevatedAssistChipColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}