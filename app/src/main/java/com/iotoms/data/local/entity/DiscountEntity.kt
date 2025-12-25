package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Fasil on 21/12/2025
 */
@Entity(tableName = "discounts")
data class DiscountEntity(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("modifiedTime")
    val modifiedTime: String? = null,

    @field:SerializedName("rate")
    val rate: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("discountType")
    val discountType: Int? = null,

    @field:SerializedName("status")
    val status: String? = null
)
