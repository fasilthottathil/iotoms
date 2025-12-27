package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.iotoms.data.model.response.FeaturesItem

/**
 * Created by Fasil on 26/12/2025
 */
@Entity(tableName = "plans")
data class PlanEntity(

    @PrimaryKey
    @field:SerializedName("code")
    val code: String,

    @field:SerializedName("features")
    val features: List<FeaturesItem?>? = null,

    @field:SerializedName("isDefault")
    val isDefault: Boolean? = null,


    @field:SerializedName("displayName")
    val displayName: String? = null,

    @field:SerializedName("sortOrder")
    val sortOrder: Int? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("registerLimit")
    val registerLimit: Int? = null
)
