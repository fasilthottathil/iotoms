package com.iotoms.data.mapper

import com.iotoms.data.local.entity.StoreEntity
import com.iotoms.data.model.response.StoreResponse
import com.iotoms.utils.extensions.getOrZero

/**
 * Created by Fasil on 26/12/2025
 */
fun StoreResponse.toStoreEntity(): StoreEntity {
    return StoreEntity(
        id = id.getOrZero(),
        name = name,
        storeType = storeType,
        promotionalImage = promotionalImage,
        promotionalMessage = promotionalMessage,
        status = status,
        modifiedTime = modifiedTime,
        venue = venue
    )
}