package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName

data class DiscountResponse(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("rate")
	val rate: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("discountType")
	val discountType: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)
