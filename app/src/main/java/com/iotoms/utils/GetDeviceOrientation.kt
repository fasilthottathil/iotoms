package com.iotoms.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.iotoms.data.enum.DeviceOrientation

/**
 * Created by Fasil on 26/10/2025
 */
@Composable
fun getDeviceOrientation(): DeviceOrientation {
    val configuration = LocalConfiguration.current
    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        DeviceOrientation.LANDSCAPE
    } else {
        DeviceOrientation.PORTRAIT
    }
}