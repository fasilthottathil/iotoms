package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable

data class UserResponse(

	@field:SerializedName("data")
	val data: List<UserDataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
@Serializable
data class UserDataItem(

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("roleId")
	val roleId: Int? = null,

	@field:SerializedName("enablePin")
	val enablePin: Boolean? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("passwordUpdatedOn")
	val passwordUpdatedOn: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
