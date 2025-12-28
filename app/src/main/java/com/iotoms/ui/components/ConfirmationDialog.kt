package com.iotoms.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

/**
 * Created by Fasil on 28/12/2025
 */
@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onYes: () -> Unit,
    onNo: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(title)
        },
        text = {
            Text(message)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onYes()
                    onDismiss()
                }
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onNo()
                    onDismiss()
                }
            ) {
                Text("No")
            }
        }
    )
}