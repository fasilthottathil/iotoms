package com.iotoms.data.mapper

import com.iotoms.data.local.entity.VenueEntity
import com.iotoms.data.model.response.VenueResponseItem
import com.iotoms.utils.extensions.getOrZero

/**
 * Created by Fasil on 26/12/2025
 */
fun VenueResponseItem.toVenueEntity(): VenueEntity {
    return VenueEntity(
        id = id.getOrZero(),
        name = name,
        status = status,
        modifiedTime = modifiedTime
    )
}