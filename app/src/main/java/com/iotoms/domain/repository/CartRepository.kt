package com.iotoms.domain.repository

import com.iotoms.data.local.entity.ItemEntity

/**
 * Created by Fasil on 30/11/2025
 */
interface CartRepository {
    suspend fun addItemToCart(itemEntity: ItemEntity)
}