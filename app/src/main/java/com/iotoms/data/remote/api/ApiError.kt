package com.iotoms.data.remote.api

import com.iotoms.utils.Error
import com.iotoms.utils.NetworkError

/**
 * Created by Fasil on 23/11/2025
 */
data class ApiError(
    val networkError: NetworkError,
    val message: String? = null
): Error