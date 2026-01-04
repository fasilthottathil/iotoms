package com.iotoms.domain.usecase.file

import com.iotoms.domain.repository.FileUploadRepository
import java.io.File

/**
 * Created by Fasil on 04/01/2026
 */
class FileUploadUseCase(private val fileUploadRepository: FileUploadRepository) {
    suspend operator fun invoke(file: File) = fileUploadRepository.uploadFile(file)
}