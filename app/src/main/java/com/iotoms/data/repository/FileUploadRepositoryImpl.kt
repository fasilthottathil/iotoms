package com.iotoms.data.repository

import com.iotoms.data.model.response.FileUploadResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.FileUploadRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.io.File

/**
 * Created by Fasil on 04/01/2026
 */
class FileUploadRepositoryImpl(
    private val client: HttpClient
) : FileUploadRepository {
    override suspend fun uploadFile(file: File): Result<FileUploadResponse, ApiError> {
        return apiRequest<FileUploadResponse> {
            client.submitFormWithBinaryData(
                url = ApiUrl.FILE_UPLOAD,
                formData = formData {
                    append(
                        key = "file",
                        value = file.readBytes(),
                        headers = Headers.build {
                            append(
                                HttpHeaders.ContentDisposition,
                                "filename=\"${file.name}\""
                            )
                        }
                    )
                }
            )
        }
    }
}