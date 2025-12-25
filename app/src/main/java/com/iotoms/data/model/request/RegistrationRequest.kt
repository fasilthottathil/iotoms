package com.iotoms.data.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("registerId")
	val registerId: Int? = null,

	@field:SerializedName("merchantTenant")
	val merchantTenant: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
