package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Fasil on 25/12/2025
 */
@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey
    @field:SerializedName("userId")
    val userId: Int? = null,

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

    @field:SerializedName("passwordUpdatedOn")
    val passwordUpdatedOn: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)
