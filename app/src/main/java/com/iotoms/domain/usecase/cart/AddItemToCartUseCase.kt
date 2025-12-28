package com.iotoms.domain.usecase.cart

import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.domain.repository.CartRepository

/**
 * Created by Fasil on 27/12/2025
 */
class AddItemToCartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(itemEntity: ItemEntity) {
        cartRepository.addItemToCart(itemEntity)
    }
}