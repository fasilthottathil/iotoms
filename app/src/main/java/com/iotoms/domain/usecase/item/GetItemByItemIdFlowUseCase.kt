package com.iotoms.domain.usecase.item

import com.iotoms.domain.repository.ItemRepository

/**
 * Created by Fasil on 01/01/2026
 */
class GetItemByItemIdFlowUseCase(private val itemRepository: ItemRepository) {
    operator fun invoke(itemId: String) = itemRepository.getItemByItemId(itemId)
}