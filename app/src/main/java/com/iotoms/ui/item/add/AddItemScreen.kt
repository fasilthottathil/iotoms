package com.iotoms.ui.item.add

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.iotoms.data.enum.DeviceOrientation
import com.iotoms.ui.theme.IconSize
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.utils.getDeviceOrientation
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 01/01/2026
 */
@Serializable
data object AddItemScreenNavKey : NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(
    onClickBack: () -> Unit
) {
    val orientation = getDeviceOrientation()
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
                        onClick = { },
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
                AddItemScreenCompact()
            }
        } else {
            Box(modifier = Modifier.padding(innerPadding)) {
                AddItemScreenExpanded()
            }
        }
    }
}