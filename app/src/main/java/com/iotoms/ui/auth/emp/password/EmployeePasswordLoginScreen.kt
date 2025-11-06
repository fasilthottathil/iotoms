package com.iotoms.ui.auth.emp.password

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
fun EmployeePasswordLoginScreen() {
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    if (getDeviceOrientation() == DeviceOrientation.PORTRAIT) {
        EmployeePasswordLoginScreenCompact(
            username = userName,
            onUsernameChange = { userName = it },
            password = password,
            onPasswordChange = { password = it },
            onLoginClick = { /*TODO*/ },
            onPinLoginClick = { /*TODO*/ }
        )
    } else {
        EmployeePasswordLoginScreenExpanded(
            username = userName,
            onUsernameChange = { userName = it },
            password = password,
            onPasswordChange = { password = it },
            onLoginClick = { /*TODO*/ },
            onPinLoginClick = { /*TODO*/ }
        )
    }
}