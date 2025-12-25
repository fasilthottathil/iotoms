package com.iotoms.ui.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import com.iotoms.data.enum.DeviceOrientation
import com.iotoms.data.model.FormError
import com.iotoms.data.model.request.LoginRequest
import com.iotoms.ui.components.LoaderDialog
import com.iotoms.utils.getDeviceOrientation
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 26/10/2025
 */
@Serializable
data object Login : NavKey

@Composable
fun LoginScreen(state: State<LoginUiState>, onLoginClick: (LoginRequest) -> Unit) {
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var domain by rememberSaveable { mutableStateOf("") }
    var registerId by rememberSaveable { mutableStateOf("") }

    var isLoading by rememberSaveable { mutableStateOf(false) }
    var formError by remember { mutableStateOf(FormError()) }
    var error by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(state.value) {
        isLoading = false
        formError = FormError()
        when (state.value) {
            is LoginUiState.Error -> {
                (state.value as LoginUiState.Error).let {
                    formError = it.formError ?: FormError()
                    error = it.message.orEmpty()
                }
            }

            LoginUiState.Idle -> {}
            LoginUiState.Loading -> isLoading = true
            LoginUiState.LoginSuccess -> {

            }
        }
    }

    fun resetFormError() {
        formError = FormError()
        error = ""
    }

    if (isLoading) {
        LoaderDialog()
    }

    if (getDeviceOrientation() == DeviceOrientation.PORTRAIT) {
        LoginScreenCompact(
            error = error,
            formError = formError,
            username = userName,
            onUsernameChange = {
                userName = it
                resetFormError()
            },
            password = password,
            onPasswordChange = {
                password = it
                resetFormError()
            },
            domain = domain,
            onDomainChange = {
                domain = it
                resetFormError()
            },
            registerId = registerId,
            onRegisterIdChange = {
                if (it.all { c -> c.isDigit() }) {
                    registerId = it
                    resetFormError()
                }
            },
            onLoginClick = {
                onLoginClick(LoginRequest(userName, password, domain, registerId))
                error = ""
            },
            onCreateAccountClick = { /*TODO*/ },
            clearError = {
                error = ""
            }
        )
    } else {
        LoginScreenExpanded(
            error = error,
            formError = formError,
            username = userName,
            onUsernameChange = {
                userName = it
                resetFormError()
            },
            password = password,
            onPasswordChange = {
                password = it
                resetFormError()
            },
            domain = domain,
            onDomainChange = {
                domain = it
                resetFormError()
            },
            registerId = registerId,
            onRegisterIdChange = {
                if (it.all { c -> c.isDigit() }) {
                    registerId = it
                    resetFormError()
                }
            },
            onLoginClick = {
                onLoginClick(LoginRequest(userName, password, domain, registerId))
                error = ""
            },
            onCreateAccountClick = { /*TODO*/ },
            clearError = {
                error = ""
            }
        )
    }
}
