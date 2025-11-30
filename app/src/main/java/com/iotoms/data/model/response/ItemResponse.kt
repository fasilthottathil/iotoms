package com.iotoms.data.model.response

import com.google.gson.annotations.SerializedName

data class ItemResponse(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("imageGallery")
	val imageGallery: ImageGallery? = null,

	@field:SerializedName("productId")
	val productId: String? = null,

	@field:SerializedName("color")
	val color: Color? = null,

	@field:SerializedName("costPrice")
	val costPrice: Int? = null,

	@field:SerializedName("upc")
	val upc: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("discount")
	val discount: DiscountResponse? = null,

	@field:SerializedName("tax")
	val tax: Tax? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("itemId")
	val itemId: String? = null,

	@field:SerializedName("itemName")
	val itemName: String? = null,

	@field:SerializedName("sellingPrice")
	val sellingPrice: Int? = null,

	@field:SerializedName("size")
	val size: Size? = null,

	@field:SerializedName("season")
	val season: String? = null,

	@field:SerializedName("style")
	val style: Style? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("department")
	val department: Department? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("subcategory")
	val subcategory: Subcategory? = null,

	@field:SerializedName("brand")
	val brand: Brand? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Subcategory(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Department(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Tax(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("taxRate2")
	val taxRate2: Int? = null,

	@field:SerializedName("taxRate1")
	val taxRate1: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Category(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Color(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Brand(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Style(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Size(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ImageGallery(

	@field:SerializedName("modifiedTime")
	val modifiedTime: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
