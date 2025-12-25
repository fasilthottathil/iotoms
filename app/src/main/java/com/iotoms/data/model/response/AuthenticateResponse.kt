package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticateResponse(

	@field:SerializedName("token")
	val token: String? = null
)
