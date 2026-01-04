package com.iotoms.domain.repository

import com.iotoms.data.model.response.FileUploadResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result
import java.io.File

/**
 * Created by Fasil on 04/01/2026
 */
interface FileUploadRepository {
    suspend fun uploadFile(file: File): Result<FileUploadResponse, ApiError>
}