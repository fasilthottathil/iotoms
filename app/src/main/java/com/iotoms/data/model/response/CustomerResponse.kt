package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName

data class CustomerResponse(

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("zipCode")
	val zipCode: String? = null,

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("mobileNumber")
	val mobileNumber: String? = null,

	@field:SerializedName("customerNumber")
	val customerNumber: String? = null,

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("landPhoneNumber")
	val landPhoneNumber: String? = null,

	@field:SerializedName("countryCode")
	val countryCode: String? = null,

	@field:SerializedName("dob")
	val dob: String? = null,

	@field:SerializedName("createdTime")
	val createdTime: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
