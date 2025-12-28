package com.iotoms.domain.usecase.cart

import com.iotoms.domain.repository.CartRepository

/**
 * Created by Fasil on 28/12/2025
 */
class AddGeneralItemToCartUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(name: String, price: Double) {
        cartRepository.addGeneralItemToCart(name, price)
    }
}