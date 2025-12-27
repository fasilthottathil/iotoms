package com.iotoms.domain.usecase.item

import com.iotoms.domain.repository.ItemRepository

/**
 * Created by Fasil on 27/12/2025
 */
class GetPaginatedItemsFromLocalUseCase(private val itemRepository: ItemRepository) {
    operator fun invoke() = itemRepository.getPaginateItemsFromLocal()
}