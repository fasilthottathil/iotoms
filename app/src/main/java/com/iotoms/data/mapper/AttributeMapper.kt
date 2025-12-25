package com.iotoms.data.mapper

import com.iotoms.data.local.entity.BrandEntity
import com.iotoms.data.local.entity.CategoryEntity
import com.iotoms.data.local.entity.ColorEntity
import com.iotoms.data.local.entity.DepartmentEntity
import com.iotoms.data.local.entity.SizeEntity
import com.iotoms.data.local.entity.StyleEntity
import com.iotoms.data.local.entity.SubCategoryEntity
import com.iotoms.data.model.response.AttributeResponse
import com.iotoms.utils.extensions.getOrZero

/**
 * Created by Fasil on 25/12/2025
 */
fun AttributeResponse.toDepartmentEntity() = DepartmentEntity(
    id = this.id.getOrZero(),
    modifiedTime = this.modifiedTime,
    name = this.name,
    status = this.status
)

fun AttributeResponse.toColorEntity() = ColorEntity(
    id = this.id.getOrZero(),
    modifiedTime = this.modifiedTime,
    name = this.name,
    status = this.status
)

fun AttributeResponse.toSizeEntity() = SizeEntity(
    id = this.id.getOrZero(),
    modifiedTime = this.modifiedTime,
    name = this.name,
    status = this.status
)

fun AttributeResponse.toStyleEntity() = StyleEntity(
    id = this.id.getOrZero(),
    modifiedTime = this.modifiedTime,
    name = this.name,
    status = this.status
)

fun AttributeResponse.toCategoryEntity() = CategoryEntity(
    id = this.id.getOrZero(),
    modifiedTime = this.modifiedTime,
    name = this.name,
    status = this.status
)

fun AttributeResponse.toSubCategoryEntity() = SubCategoryEntity(
    id = this.id.getOrZero(),
    modifiedTime = this.modifiedTime,
    name = this.name,
    status = this.status
)

fun AttributeResponse.toBrandEntity() = BrandEntity(
    id = this.id.getOrZero(),
    modifiedTime = this.modifiedTime,
    name = this.name,
    status = this.status
)