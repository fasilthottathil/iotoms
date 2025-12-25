package com.iotoms.data.mapper

import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.model.response.ItemResponse

/**
 * Created by Fasil on 25/12/2025
 */
fun ItemResponse.toItemEntity() = ItemEntity(
    modifiedTime = modifiedTime,
    imageGallery = imageGallery,
    productId = productId,
    color = color,
    costPrice = costPrice,
    upc = upc,
    description = description,
    discountIds = discount?.id?.let { listOf(it) },
    taxIds = tax?.id?.let { listOf(it) },
    type = type,
    itemId = itemId.orEmpty(),
    itemName = itemName,
    sellingPrice = sellingPrice?.toDouble(),
    sizeId = size?.id,
    season = season,
    styleId = style?.id,
    id = id,
    departmentId = department?.id,
    categoryId = category?.id,
    subcategoryId = subcategory?.id,
    brandId = brand?.id,
    status = status
)
