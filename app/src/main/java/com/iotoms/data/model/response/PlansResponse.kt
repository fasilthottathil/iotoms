package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
@Serializable
data class PlansResponse(

	@field:SerializedName("data")
	val data: List<PlansData?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
@Serializable
data class FeaturesItem(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)

@Serializable
data class PlansData(

	@field:SerializedName("features")
	val features: List<FeaturesItem?>? = null,

	@field:SerializedName("isDefault")
	val isDefault: Boolean? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("sortOrder")
	val sortOrder: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("registerLimit")
	val registerLimit: Int? = null
)
