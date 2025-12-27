package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Fasil on 27/12/2025
 */
@Entity(tableName = "branding")
data class BrandingEntity(

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("modifiedTime")
    val modifiedTime: String? = null,

    @field:SerializedName("footer")
    val footer: String? = null,

    @field:SerializedName("logo")
    val logo: String? = null,

    @field:SerializedName("header")
    val header: String? = null,

    @field:SerializedName("store")
    val storeId: Int? = null,

    @field:SerializedName("additionalAttributes")
    val additionalAttributes: String? = null
)
