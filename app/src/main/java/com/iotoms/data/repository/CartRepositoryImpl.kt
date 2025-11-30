package com.iotoms.data.repository

import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.domain.repository.CartRepository

/**
 * Created by Fasil on 30/11/2025
 */
class CartRepositoryImpl(private val appDatabase: AppDatabase): CartRepository {
    override suspend fun addItemToCart(itemEntity: ItemEntity) {
        val cartItemEntity = CartItemEntity(
            id = System.currentTimeMillis().toString(),
            itemId = itemEntity.itemId.orEmpty(),
            upc = listOf(itemEntity.upc.orEmpty()),
            name = itemEntity.itemName.orEmpty(),
            price = itemEntity.sellingPrice ?: 0.0,
            quantity = 1.0
        )
    }
}