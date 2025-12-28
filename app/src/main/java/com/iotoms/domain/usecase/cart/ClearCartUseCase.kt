package com.iotoms.domain.usecase.cart

import com.iotoms.domain.repository.CartRepository

/**
 * Created by Fasil on 28/12/2025
 */
class ClearCartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke() {
        cartRepository.clearCart()
    }
}