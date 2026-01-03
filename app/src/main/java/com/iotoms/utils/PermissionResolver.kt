package com.iotoms.utils

import android.Manifest
import android.os.Build

/**
 * Created by Fasil on 03/01/2026
 */
object PermissionResolver {
    fun galleryPermission(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            Manifest.permission.READ_MEDIA_IMAGES
        else
            Manifest.permission.READ_EXTERNAL_STORAGE

    const val CAMERA = Manifest.permission.CAMERA
}