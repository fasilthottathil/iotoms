package com.iotoms.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.NeutralGray600
import com.iotoms.ui.theme.NeutralGray900

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun LoaderDialog(
    modifier: Modifier = Modifier,
    text: String = "Processing...",
    onDismissRequest: () -> Unit = {},
    canDismiss: Boolean = false,
    isPlatformWidth: Boolean = false
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        content = {
            Column(
                modifier = modifier
                    .widthIn(min = 150.dp)
                    .heightIn(min = 150.dp)
                    .background(
                        color = NeutralGray600.copy(alpha = 0.8f),
                        shape = ShapeDefaults.Large
                    )
                    .padding(MediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = NeutralGray900)
                Spacer(Modifier.Companion.padding(ExtraSmallPadding))
                Text(text, color = NeutralGray900)
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = canDismiss,
            dismissOnClickOutside = canDismiss,
            usePlatformDefaultWidth = isPlatformWidth
        )
    )
}