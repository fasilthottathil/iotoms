package com.iotoms.domain.usecase.item

import com.iotoms.domain.repository.ItemRepository

/**
 * Created by Fasil on 29/12/2025
 */
class SearchItemFromLocalUseCase(private val itemRepository: ItemRepository) {
    operator fun invoke(query: String) = itemRepository.searchItemsFromLocal(query)
}