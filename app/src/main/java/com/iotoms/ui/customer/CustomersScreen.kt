package com.iotoms.ui.customer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iotoms.data.enum.DeviceOrientation
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.NeutralGray50
import com.iotoms.ui.theme.PrimaryTealLight
import com.iotoms.utils.getDeviceOrientation

/**
 * Created by Fasil on 08/11/2025
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomersScreen() {
    val orientation = getDeviceOrientation()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text("Customers")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    if (orientation == DeviceOrientation.LANDSCAPE) {
                        ExtendedFloatingActionButton(
                            onClick = {},
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null
                                )
                                Text("Add Customer")
                            },
                            containerColor = PrimaryTealLight,
                            contentColor = NeutralGray50
                        )
                        Spacer(Modifier.width(ExtraSmallPadding))
                    }
                }
            )
        },
        floatingActionButton = {
            if (orientation == DeviceOrientation.PORTRAIT) {
                FloatingActionButton(
                    onClick = {},
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        if (orientation == DeviceOrientation.PORTRAIT) {
            CustomersScreenCompact(modifier = modifier)
        } else {
            CustomersScreenExpanded(modifier = modifier)
        }
    }
}