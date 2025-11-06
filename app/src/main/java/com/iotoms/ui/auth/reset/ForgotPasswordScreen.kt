package com.iotoms.ui.auth.reset

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
fun ForgotPasswordScreen() {
    var userName by rememberSaveable { mutableStateOf("") }
    if (getDeviceOrientation() == DeviceOrientation.PORTRAIT) {
        ForgotPasswordScreenCompact(
            username = userName,
            onUsernameChange = { userName = it },
            onResetClick = { /*TODO*/ },
            onLoginClick = { /*TODO*/ }
        )
    } else {
        ForgotPasswordScreenExpanded(
            username = userName,
            onUsernameChange = { userName = it },
            onResetClick = { /*TODO*/ },
            onLoginClick = { /*TODO*/ }
        )
    }
}