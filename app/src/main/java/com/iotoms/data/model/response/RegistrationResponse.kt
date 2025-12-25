package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(

    @field:SerializedName("data")
    val data: RegistrationData? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)

@Serializable
data class RegistrationData(

    @field:SerializedName("registerId")
    val registerId: Int? = null,

    @field:SerializedName("tenant")
    val tenant: String? = null,

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)
