package com.iotoms.data.model

import com.iotoms.data.enum.DiscountApplyMode
import com.iotoms.data.enum.DiscountApplyType
import com.iotoms.data.enum.DiscountType
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 29/11/2025
 */
@Serializable
data class CartDiscount(
    var discountId: Int,
    var description: String,
    var discountRate: Double,
    var discount: Double,
    var discountType: DiscountType? = DiscountType.fromId(discountId),
    var applyMode: DiscountApplyMode,
    var discountApplyType: DiscountApplyType
)
