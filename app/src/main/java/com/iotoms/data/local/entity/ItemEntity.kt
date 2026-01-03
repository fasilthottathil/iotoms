package com.iotoms.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iotoms.data.model.response.Color
import com.iotoms.data.model.response.ImageGallery

/**
 * Created by Fasil on 30/11/2025
 */
@Entity(tableName = "items")
data class ItemEntity(

    @PrimaryKey
    var itemId: String,

    var modifiedTime: String? = null,

    var imageGallery: ImageGallery? = null,

    var productId: String? = null,

    var color: Color? = null,

    var costPrice: Double? = null,

    var upc: String? = null,

    var description: String? = null,

    val discountIds: List<Int>? = null,

    val taxIds: List<Int>? = null,

    var type: String? = null,

    var itemName: String? = null,

    var sellingPrice: Double? = null,

    var sizeId: Int? = null,

    var size: String? = null,

    var season: String? = null,

    var styleId: Int? = null, 
    
    var style: String? = null,

    var id: Int? = null,

    var departmentId: Int? = null,

    var department: String? = null,

    var categoryId: Int? = null,
    
    var category: String? = null,

    var subcategoryId: Int? = null,
    
    var subcategory: String? = null,

    var brandId: Int? = null,
    
    var brand: String? = null,

    var status: String? = null
)