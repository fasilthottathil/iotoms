package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BrandingResponse(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("footer")
	val footer: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("header")
	val header: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("store")
	val store: StoreResponse? = null,

	@field:SerializedName("additionalAttributes")
	val additionalAttributes: String? = null
)
