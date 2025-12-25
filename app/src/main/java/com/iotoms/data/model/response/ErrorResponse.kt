package com.iotoms.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 23/11/2025
 */
@Serializable
data class ErrorResponse(
    @SerialName("message")
    val message: String?
)