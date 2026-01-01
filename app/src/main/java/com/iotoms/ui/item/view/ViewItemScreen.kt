package com.iotoms.ui.item.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.iotoms.data.enum.DeviceOrientation
import com.iotoms.ui.theme.IconSize
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.utils.getDeviceOrientation
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 29/12/2025
 */
@Serializable
data class ViewItemScreenNavKey(val itemId: String): NavKey
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewItemScreen(
    uiState: State<ViewItemUiState>,
    onClickBack: () -> Unit
) {
    val orientation = getDeviceOrientation()
    val itemEntity = when (val state = uiState.value) {
        is ViewItemUiState.Data -> state.itemEntity
        else -> null
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
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
                        Text("#${itemEntity?.itemId}")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                content = {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                }
            )
        }
    ) { innerPadding ->
        if (orientation == DeviceOrientation.PORTRAIT) {
            ViewItemScreenCompact(modifier = Modifier.padding(innerPadding), itemEntity = itemEntity)
        } else {
            ViewItemScreenExpanded(modifier = Modifier.padding(innerPadding), itemEntity = itemEntity)
        }
    }
}