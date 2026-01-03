package com.iotoms.ui.item.add

import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation3.runtime.NavKey
import com.iotoms.data.enum.DeviceOrientation
import com.iotoms.ui.theme.IconSize
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.utils.PermissionResolver
import com.iotoms.utils.createCameraUri
import com.iotoms.utils.getDeviceOrientation
import com.iotoms.utils.uriToFile
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 01/01/2026
 */
@Serializable
data object AddItemScreenNavKey : NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    uiState: State<AddItemScreenUiState>,
    onClickAttr: (String) -> Unit,
    onClickBack: () -> Unit,
    onClickSave: () -> Unit,
    onValueChange: () -> Unit
) {
    val orientation = getDeviceOrientation()
    val context = LocalContext.current
    var pendingAction by remember { mutableStateOf<(() -> Unit)?>(null) }
    var cameraUri by remember { mutableStateOf<Uri?>(null) }

    // ---- Permission Launcher ----
    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                pendingAction?.invoke()
            } else {
                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            }
            pendingAction = null
        }

    // ---- Gallery Picker ----
    val galleryLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.PickVisualMedia()
        ) { uri ->
            uri?.let {
                uiState.value.imageFile = uriToFile(context, it)
                onValueChange()
            }
        }

    // ---- Camera Launcher ----
    val cameraLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { success ->
            if (success) {
                cameraUri?.let {
                    uiState.value.imageFile = uriToFile(context, it)
                    onValueChange()
                }
            }
        }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier
                                .height(IconSize)
                                .clickable(onClick = onClickBack)
                        )
                        Spacer(Modifier.width(SmallPadding))
                        Text("Add Item")
                    }
                },
                actions = {
                    TextButton(
                        onClick = onClickSave,
                        content = {
                            Icon(
                                imageVector = Icons.Outlined.Save,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.inverseSurface
                            )
                            Text("Save", color =  MaterialTheme.colorScheme.inverseSurface)
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        if (orientation == DeviceOrientation.PORTRAIT) {
            Box(modifier = Modifier.padding(innerPadding)) {
                AddItemScreenCompact(
                    uiState = uiState,
                    onClickAttr = onClickAttr,
                    onAddPhoto = {
                        val permission = PermissionResolver.galleryPermission()
                        if (ContextCompat.checkSelfPermission(context, permission)
                            == PackageManager.PERMISSION_GRANTED
                        ) {
                            galleryLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        } else {
                            pendingAction = {
                                galleryLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                            permissionLauncher.launch(permission)
                        }
                    },
                    onTakePhoto = {
                        if (ContextCompat.checkSelfPermission(
                                context,
                                PermissionResolver.CAMERA
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            cameraUri = createCameraUri(context)
                            cameraLauncher.launch(cameraUri!!)
                        } else {
                            pendingAction = {
                                cameraUri = createCameraUri(context)
                                cameraLauncher.launch(cameraUri!!)
                            }
                            permissionLauncher.launch(PermissionResolver.CAMERA)
                        }
                    }
                )
            }
        } else {
            Box(modifier = Modifier.padding(innerPadding)) {
                AddItemScreenExpanded(uiState)
            }
        }
    }
}