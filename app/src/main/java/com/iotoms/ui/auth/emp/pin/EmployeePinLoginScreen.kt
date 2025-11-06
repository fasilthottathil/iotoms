package com.iotoms.ui.auth.emp.pin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.iotoms.data.enum.DeviceOrientation
import com.iotoms.utils.getDeviceOrientation

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun EmployeePinLoginScreen() {
    var pin by rememberSaveable { mutableStateOf("") }
    if (getDeviceOrientation() == DeviceOrientation.PORTRAIT) {
        EmployeePinLoginScreenCompact(
            pin = pin,
            onPinChange = { pin = it },
            onLoginClick = { /*TODO*/ },
            onPasswordLoginClick = { /*TODO*/ }
        )
    } else {
        EmployeePinLoginScreenExpanded(
            pin = pin,
            onPinChange = { pin = it },
            onLoginClick = { /*TODO*/ },
            onPasswordLoginClick = { /*TODO*/ }
        )
    }
}