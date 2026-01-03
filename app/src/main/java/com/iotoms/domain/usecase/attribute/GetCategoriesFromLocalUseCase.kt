package com.iotoms.domain.usecase.attribute

import com.iotoms.domain.repository.AttributeRepository

/**
 * Created by Fasil on 03/01/2026
 */
class GetCategoriesFromLocalUseCase(private val attributeRepository: AttributeRepository) {
    operator fun invoke() = attributeRepository.getCategoriesFromLocal()
}