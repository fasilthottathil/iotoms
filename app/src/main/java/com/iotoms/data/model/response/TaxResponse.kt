package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName

data class TaxResponse(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("taxRate2")
	val taxRate2: Int? = null,

	@field:SerializedName("taxRate1")
	val taxRate1: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)
