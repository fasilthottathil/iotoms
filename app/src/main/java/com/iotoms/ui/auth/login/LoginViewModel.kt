package com.iotoms.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iotoms.data.model.FormError
import com.iotoms.data.model.request.LoginRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay

/**
 * Created by Fasil on 22/11/2025
 */
class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LoginUiState.Idle
    )

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            _uiState.update { LoginUiState.Idle }
            delay(100L)
            if (request.username.isEmpty()) {
                _uiState.update { LoginUiState.Error(formError = FormError("username" to "Username cannot be empty")) }
                return@launch
            }
            if (request.password.isEmpty()) {
                _uiState.update { LoginUiState.Error(formError = FormError("password" to "Password cannot be empty")) }
                return@launch
            }
            if (request.password.length < 5) {
                _uiState.update { LoginUiState.Error(formError = FormError("password" to "Password length should be greater than 4 characters")) }
                return@launch
            }
            if (request.domain.isEmpty()) {
                _uiState.update { LoginUiState.Error(formError = FormError("domain" to "Domain cannot be empty")) }
                return@launch
            }
            if (request.registerId.isEmpty()) {
                _uiState.update { LoginUiState.Error(formError = FormError("regId" to "Register Id cannot be empty")) }
                return@launch
            }
        }
    }
}