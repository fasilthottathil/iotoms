package com.iotoms.ui.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.iotoms.data.enum.DeviceOrientation
import com.iotoms.utils.getDeviceOrientation

/**
 * Created by Fasil on 26/10/2025
 */
@Composable
@Preview(showBackground = true)
fun LoginScreen() {
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var domain by rememberSaveable { mutableStateOf("") }
    var registerId by rememberSaveable { mutableStateOf("") }
    if (getDeviceOrientation() == DeviceOrientation.PORTRAIT) {
        LoginScreenCompact(
            username = userName,
            onUsernameChange = { userName = it },
            password = password,
            onPasswordChange = { password = it },
            domain = domain,
            onDomainChange = { domain = it },
            registerId = registerId,
            onRegisterIdChange = {
                if (it.all { c -> c.isDigit() }) {
                    registerId = it
                }
            },
            onLoginClick = { /*TODO*/ },
            onCreateAccountClick = { /*TODO*/ }
        )
    } else {
        LoginScreenExpanded(
            username = userName,
            onUsernameChange = { userName = it },
            password = password,
            onPasswordChange = { password = it },
            domain = domain,
            onDomainChange = { domain = it },
            registerId = registerId,
            onRegisterIdChange = {
                if (it.all { c -> c.isDigit() }) {
                    registerId = it
                }
            },
            onLoginClick = { /*TODO*/ },
            onCreateAccountClick = { /*TODO*/ }
        )
    }
}
