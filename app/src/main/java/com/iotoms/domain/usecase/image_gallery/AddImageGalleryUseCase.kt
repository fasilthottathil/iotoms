package com.iotoms.domain.usecase.image_gallery

import com.iotoms.data.model.request.ImageGalleryRequest
import com.iotoms.domain.repository.ImageGalleryRepository

/**
 * Created by Fasil on 04/01/2026
 */
class AddImageGalleryUseCase(private val imageGalleryRepository: ImageGalleryRepository) {
    suspend operator fun invoke(imageGalleryRequest: ImageGalleryRequest) =
        imageGalleryRepository.addImageGallery(imageGalleryRequest)
}