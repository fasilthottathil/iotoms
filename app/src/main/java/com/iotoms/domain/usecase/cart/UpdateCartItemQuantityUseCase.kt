package com.iotoms.domain.usecase.cart

import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.domain.repository.CartRepository

/**
 * Created by Fasil on 28/12/2025
 */
class UpdateCartItemQuantityUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(cartItemEntity: CartItemEntity) {
        cartRepository.updateQuantity(cartItemEntity)
    }
}