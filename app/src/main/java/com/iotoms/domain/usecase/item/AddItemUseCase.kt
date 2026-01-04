package com.iotoms.domain.usecase.item

import com.iotoms.data.model.request.AddItemRequest
import com.iotoms.domain.repository.ItemRepository

/**
 * Created by Fasil on 04/01/2026
 */
class AddItemUseCase(private val itemRepository: ItemRepository) {
    suspend operator fun invoke(addItemRequest: AddItemRequest) = itemRepository.addItem(addItemRequest)
}