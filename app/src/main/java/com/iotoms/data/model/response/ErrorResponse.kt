package com.iotoms.data.model.response

import kotlinx.serialization.SerialName

/**
 * Created by Fasil on 23/11/2025
 */
data class ErrorResponse(
    @SerialName("message")
    val message: String?
)