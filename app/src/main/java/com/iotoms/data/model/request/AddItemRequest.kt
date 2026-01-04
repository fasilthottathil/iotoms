package com.iotoms.data.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AddItemRequest(

	@field:SerializedName("sizeId")
	val sizeId: Int? = null,

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("productId")
	val productId: String? = null,

	@field:SerializedName("colorId")
	val colorId: Int? = null,

	@field:SerializedName("departmentId")
	val departmentId: Int? = null,

	@field:SerializedName("costPrice")
	val costPrice: Double? = null,

	@field:SerializedName("upc")
	val upc: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("itemId")
	val itemId: String? = null,

	@field:SerializedName("itemName")
	val itemName: String? = null,

	@field:SerializedName("sellingPrice")
	val sellingPrice: Double? = null,

	@field:SerializedName("styleId")
	val styleId: Int? = null,

	@field:SerializedName("taxId")
	val taxId: Int? = null,

	@field:SerializedName("brandId")
	val brandId: Int? = null,

	@field:SerializedName("subcategoryId")
	val subcategoryId: Int? = null,

	@field:SerializedName("season")
	val season: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("discountId")
	val discountId: Int? = null,

	@field:SerializedName("categoryId")
	val categoryId: Int? = null,

	@field:SerializedName("imageGalleryId")
	val imageGalleryId: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)
