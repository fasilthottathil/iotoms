package com.iotoms.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iotoms.ui.theme.ButtonHeight
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.PrimaryTealLight

/**
 * Created by Fasil on 01/11/2025
 */
@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = ButtonHeight),
        content = {
            if (leadingIcon != null) {
                leadingIcon()
                Spacer(modifier = Modifier.Companion.width(ExtraSmallPadding))
            }
            Text(text)
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.Companion.width(ExtraSmallPadding))
                trailingIcon()
            }
        },
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = PrimaryTealLight,
        ),
        shape = ShapeDefaults.Medium
    )
}