package com.iotoms.domain.repository

import com.iotoms.data.model.request.ImageGalleryRequest
import com.iotoms.data.model.response.ImageGallery
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result

/**
 * Created by Fasil on 04/01/2026
 */
interface ImageGalleryRepository {
    suspend fun addImageGallery(imageGalleryRequest: ImageGalleryRequest): Result<ImageGallery, ApiError>
}