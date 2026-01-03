package com.iotoms.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.PrimaryTealDark
import com.iotoms.utils.extensions.dashedBorder

/**
 * Created by Fasil on 03/01/2026
 */
@Composable
fun ImageUploadSection(
    onAddPhoto: () -> Unit,
    onTakePhoto: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .dashedBorder(
                strokeWidth = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                cornerRadius = 12.dp
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Outlined.PhotoCamera,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Add item image",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    onClick = onAddPhoto,
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryTealDark,
                        contentColor = NeutralGray50
                    )
                ) {
                    Text("Add Photo")
                }

                OutlinedButton(
                    onClick = onTakePhoto,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Take Photo")
                }
            }
        }
    }
}

@Preview
@Composable
private fun ImageUploadSectionPreview() {
    ImageUploadSection(
        onAddPhoto = {},
        onTakePhoto = {}
    )
}