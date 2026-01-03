package com.iotoms.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

/**
 * Created by Fasil on 03/01/2026
 */
fun uriToFile(context: Context, uri: Uri): File {
    val input = context.contentResolver.openInputStream(uri)
        ?: error("Cannot open uri")

    val file = File(context.cacheDir, "upload_${System.currentTimeMillis()}.jpg")

    file.outputStream().use { output ->
        input.copyTo(output)
    }
    return file
}

fun createCameraUri(context: Context): Uri {
    val file = File(
        context.cacheDir,
        "camera_${System.currentTimeMillis()}.jpg"
    )
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        file
    )
}