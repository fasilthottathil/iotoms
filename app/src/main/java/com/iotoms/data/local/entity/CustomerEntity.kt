package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Fasil on 26/12/2025
 */
@Entity(tableName = "customers")
data class CustomerEntity(

    @PrimaryKey
    @field:SerializedName("customerNumber")
    val customerNumber: String,

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
