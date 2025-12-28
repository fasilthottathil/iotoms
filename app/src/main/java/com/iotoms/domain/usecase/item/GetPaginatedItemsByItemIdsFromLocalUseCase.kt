package com.iotoms.domain.usecase.item

import com.iotoms.domain.repository.ItemRepository

/**
 * Created by Fasil on 27/12/2025
 */
class GetPaginatedItemsByItemIdsFromLocalUseCase(private val itemRepository: ItemRepository) {
    operator fun invoke(ids: List<String>) = itemRepository.getPaginateItemsByItemIdsFromLocal(ids)
}