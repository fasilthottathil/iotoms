package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class StoreResponse(

	@field:SerializedName("venue")
	val venue: VenueResponseItem? = null,

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("storeType")
	val storeType: String? = null,

	@field:SerializedName("promotionalImage")
	val promotionalImage: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("promotionalMessage")
	val promotionalMessage: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
