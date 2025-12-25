package com.iotoms.ui.auth.login

import com.iotoms.data.model.FormError

/**
 * Created by Fasil on 22/11/2025
 */
sealed class LoginUiState {
    data object Idle : LoginUiState()
    data object Loading : LoginUiState()
    data object LoginSuccess : LoginUiState()
    data class Error(val message: String? = null, val formError: FormError? = null) : LoginUiState()
}