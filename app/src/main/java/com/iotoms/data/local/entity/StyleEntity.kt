package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Fasil on 25/12/2025
 */
@Entity(tableName = "styles")
data class StyleEntity(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("modifiedTime")
    val modifiedTime: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)
