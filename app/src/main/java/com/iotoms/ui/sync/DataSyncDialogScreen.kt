package com.iotoms.ui.sync

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation3.runtime.NavKey
import com.iotoms.ui.theme.MediumPadding
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 26/12/2025
 */

@Serializable
data object DataSync : NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataSyncDialogScreen(
    uiState: State<DataSyncUiState>,
    onDismiss: () -> Unit,
    onSync: (Boolean) -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val progress = remember { mutableFloatStateOf(0f) }

    LaunchedEffect(uiState.value) {
        when (uiState.value) {
            DataSyncUiState.SyncSuccess -> {
                onDismiss()
                onSync(true)
            }

            DataSyncUiState.Idle -> {
                // Do nothing
            }

            is DataSyncUiState.Progress -> {
                progress.floatValue = (uiState.value as DataSyncUiState.Progress).progress
            }

            DataSyncUiState.Loading -> {

            }
        }
    }

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 8000, // ⬅️ slower = bigger value
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotationAnim"
    )
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text("Data Sync")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 300.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Sync,
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .graphicsLayer {
                                rotationZ = rotation
                            }
                    )
                    LinearProgressIndicator(
                        progress = {
                            (progress.floatValue.coerceIn(0f, 10f))
                        },
                        strokeCap = StrokeCap.Round
                    )
                    Spacer(Modifier.height(MediumPadding))
                    Text("Data synchronization is in progress...")
                }
            }
        }
    }
}