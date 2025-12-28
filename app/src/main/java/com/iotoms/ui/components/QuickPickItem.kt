package com.iotoms.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.iotoms.ui.cart.QuickPick
import com.iotoms.ui.theme.ButtonHeight
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.PrimaryTeal

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun QuickPickItem(
    quickPick: QuickPick,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    ElevatedAssistChip(
        modifier = Modifier.height(ButtonHeight),
        onClick = onClick,
        label = {
            Text(quickPick.label)
        },
        border = BorderStroke(1.dp, color = PrimaryTeal),
        colors = AssistChipDefaults.elevatedAssistChipColors(
            containerColor = if (isSelected) {
                PrimaryTeal
            } else {
                if (quickPick.backgroundColor.isNullOrEmpty()) {
                    try {
                        Color(quickPick.backgroundColor!!.toColorInt())
                    } catch (_: Exception) {
                        MaterialTheme.colorScheme.background
                    }
                } else {
                    MaterialTheme.colorScheme.background
                }
            },
            labelColor = if (isSelected) {
                NeutralGray50
            } else {
                if (quickPick.id == "All Items") {
                    PrimaryTeal
                } else {
                    Color.Unspecified
                }
            }
        )
    )
}