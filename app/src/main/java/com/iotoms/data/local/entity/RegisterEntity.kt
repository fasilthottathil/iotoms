package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.iotoms.data.model.response.StoreResponse

/**
 * Created by Fasil on 27/12/2025
 */
@Entity(tableName = "registers")
data class RegisterEntity(

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("modifiedTime")
    val modifiedTime: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("fromTime")
    val fromTime: String? = null,

    @field:SerializedName("store")
    val store: StoreResponse? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("toTime")
    val toTime: String? = null,

    @field:SerializedName("terminationReason")
    val terminationReason: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)
