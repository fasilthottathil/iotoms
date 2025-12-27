package com.iotoms.data.mapper

import com.iotoms.data.local.entity.RegisterEntity
import com.iotoms.data.model.response.RegisterResponse
import com.iotoms.utils.extensions.getOrZero

/**
 * Created by Fasil on 27/12/2025
 */
fun RegisterResponse.toRegisterEntity(): RegisterEntity {
    return RegisterEntity(
        id = id.getOrZero(),
        name = name,
        type = type,
        status = status,
        terminationReason = terminationReason,
        fromTime = fromTime,
        toTime = toTime,
        modifiedTime = modifiedTime,
        store = store
    )
}