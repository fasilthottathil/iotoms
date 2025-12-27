package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("fromTime")
	val fromTime: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("store")
	val store: StoreResponse? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("toTime")
	val toTime: String? = null,

	@field:SerializedName("terminationReason")
	val terminationReason: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
