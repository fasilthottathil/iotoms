package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.iotoms.data.model.response.QuickPickButtonsItem

/**
 * Created by Fasil on 28/12/2025
 */
@Entity(tableName = "quick_picks")
data class QuickPickEntity(

    @PrimaryKey
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("backgroundColor")
    val backgroundColor: String? = null,

    @field:SerializedName("buttons")
    val buttons: List<QuickPickButtonsItem?>? = null,

    @field:SerializedName("pageIndex")
    val pageIndex: Int? = null,

    @field:SerializedName("title")
    val title: String? = null
 )
