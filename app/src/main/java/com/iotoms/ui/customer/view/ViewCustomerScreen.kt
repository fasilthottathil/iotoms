package com.iotoms.ui.customer.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.iotoms.data.enum.DeviceOrientation
import com.iotoms.utils.getDeviceOrientation

/**
 * Created by Fasil on 08/11/2025
 */
@Composable
fun ViewCustomerScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                }, content = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                }
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        if (getDeviceOrientation() == DeviceOrientation.PORTRAIT) {
            ViewCustomerScreenCompact(modifier)
        } else {
            ViewCustomerScreenExpanded(modifier)
        }
    }
}

@Preview
@Composable
private fun ViewCustomerScreenPreview() {
    ViewCustomerScreen()
}
