package com.iotoms.data.mapper

import com.iotoms.data.local.entity.BrandingEntity
import com.iotoms.data.model.response.BrandingResponse
import com.iotoms.utils.extensions.getOrZero

/**
 * Created by Fasil on 27/12/2025
 */
fun BrandingResponse.toBrandingEntity(): BrandingEntity {
    return BrandingEntity(
        id = id.getOrZero(),
        modifiedTime = modifiedTime,
        footer = footer,
        logo = logo,
        header = header,
        storeId = store?.id,
        additionalAttributes = additionalAttributes
    )
}