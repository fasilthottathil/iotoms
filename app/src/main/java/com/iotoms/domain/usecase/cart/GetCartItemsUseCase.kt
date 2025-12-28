package com.iotoms.domain.usecase.cart

import com.iotoms.domain.repository.CartRepository

/**
 * Created by Fasil on 27/12/2025
 */
class GetCartItemsUseCase(private val cartRepository: CartRepository) {
    suspend operator fun invoke(transactionNumber: String) = cartRepository.getCartItems(transactionNumber)
}