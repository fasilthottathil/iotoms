package com.iotoms.domain.usecase.cart

import com.iotoms.domain.repository.CartRepository

/**
 * Created by Fasil on 27/12/2025
 */
class GetCartAsFlowUseCase(private val cartRepository: CartRepository) {
    operator fun invoke() = cartRepository.getCart()
}