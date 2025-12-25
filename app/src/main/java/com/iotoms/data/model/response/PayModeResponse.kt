package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PayModeResponse(

	@field:SerializedName("sequence")
	val sequence: Int? = null,

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)
