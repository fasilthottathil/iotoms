package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.iotoms.data.model.response.Color
import com.iotoms.data.model.response.ImageGallery

/**
 * Created by Fasil on 30/11/2025
 */
@Entity(tableName = "items")
data class ItemEntity(

    @PrimaryKey
    @field:SerializedName("itemId")
    val itemId: String,

    @field:SerializedName("modifiedTime")
    val modifiedTime: String? = null,

    @field:SerializedName("imageGallery")
    val imageGallery: ImageGallery? = null,

    @field:SerializedName("productId")
    val productId: String? = null,

    @field:SerializedName("color")
    val color: Color? = null,

    @field:SerializedName("costPrice")
    val costPrice: Double? = null,

    @field:SerializedName("upc")
    val upc: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("discountId")
    val discountIds: List<Int>? = null,

    @field:SerializedName("taxId")
    val taxIds: List<Int>? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("itemName")
    val itemName: String? = null,

    @field:SerializedName("sellingPrice")
    val sellingPrice: Double? = null,

    @field:SerializedName("sizeId")
    val sizeId: Int? = null,

    @field:SerializedName("season")
    val season: String? = null,

    @field:SerializedName("styleId")
    val styleId: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("departmentId")
    val departmentId: Int? = null,

    @field:SerializedName("categoryId")
    val categoryId: Int? = null,

    @field:SerializedName("subcategoryId")
    val subcategoryId: Int? = null,

    @field:SerializedName("brandId")
    val brandId: Int? = null,

    @field:SerializedName("status")
    val status: String? = null
)