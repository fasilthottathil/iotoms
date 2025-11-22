package com.iotoms.data.model.request

import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 22/11/2025
 */
@Serializable
data class LoginRequest(
    val username: String,
    val password: String,
    val domain: String,
    val registerId: String
)
