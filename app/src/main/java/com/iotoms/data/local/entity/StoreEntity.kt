package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.iotoms.data.model.response.VenueResponseItem

/**
 * Created by Fasil on 26/12/2025
 */
@Entity(tableName = "stores")
data class StoreEntity(

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("venue")
    val venue: VenueResponseItem? = null,

    @field:SerializedName("modifiedTime")
    val modifiedTime: String? = null,

    @field:SerializedName("storeType")
    val storeType: String? = null,

    @field:SerializedName("promotionalImage")
    val promotionalImage: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("promotionalMessage")
    val promotionalMessage: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)
