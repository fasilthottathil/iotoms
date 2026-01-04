package com.iotoms.data.mapper

import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.model.request.AddItemRequest
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
    sellingPrice = sellingPrice,
    sizeId = size?.id,
    size = size?.name,
    season = season,
    styleId = style?.id,
    style = style?.name,
    id = id,
    departmentId = department?.id,
    department = department?.name,
    categoryId = category?.id,
    category = category?.name,
    subcategoryId = subcategory?.id,
    subcategory = subcategory?.name,
    brandId = brand?.id,
    brand = brand?.name,
    status = status
)


fun ItemEntity.toAddItemRequest() = AddItemRequest(
    itemId = itemId,
    productId = productId,
    itemName = itemName,
    sizeId = sizeId,
    departmentId = departmentId,
    categoryId = categoryId,
    subcategoryId = subcategoryId,
    brandId = brandId,
    colorId = color?.id,
    sellingPrice = sellingPrice,
    costPrice = costPrice,
    upc = upc,
    discountId = discountIds?.firstOrNull(),
    taxId = taxIds?.firstOrNull(),
    description = description,
    imageGalleryId = imageGallery?.id,
    type = "STORE_ITEM"
)
