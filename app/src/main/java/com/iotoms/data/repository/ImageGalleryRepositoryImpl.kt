package com.iotoms.data.repository

import com.iotoms.data.model.request.ImageGalleryRequest
import com.iotoms.data.model.response.ImageGallery
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.ImageGalleryRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

/**
 * Created by Fasil on 04/01/2026
 */
class ImageGalleryRepositoryImpl(
    private val httpClient: HttpClient
) : ImageGalleryRepository {
    override suspend fun addImageGallery(imageGalleryRequest: ImageGalleryRequest): Result<ImageGallery, ApiError> {
        return apiRequest {
            httpClient.post(ApiUrl.IMAGE_GALLERY) {
               setBody(imageGalleryRequest)
            }
        }
    }

}