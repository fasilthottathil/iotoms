package com.iotoms.data.mapper

import com.iotoms.data.local.entity.PlanEntity
import com.iotoms.data.model.response.PlansData

/**
 * Created by Fasil on 26/12/2025
 */
fun PlansData.toPlanEntity(): PlanEntity {
    return PlanEntity(
        code = this.code.orEmpty(),
        features = this.features,
        isDefault = this.isDefault,
        displayName = this.displayName,
        sortOrder = this.sortOrder,
        description = this.description,
        registerLimit = this.registerLimit
    )
}