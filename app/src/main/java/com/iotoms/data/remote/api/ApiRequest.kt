package com.iotoms.data.remote.api

import com.iotoms.data.model.response.ErrorResponse
import com.iotoms.utils.NetworkError
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import com.iotoms.utils.Result
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

/**
 * Created by Fasil on 23/11/2025
 */
suspend inline fun <reified T> apiRequest(call: () -> HttpResponse): Result<T, ApiError> {
    val response = try {
        call()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(ApiError(NetworkError.NO_INTERNET, e.message))
    } catch (e: SerializationException) {
        return Result.Error(ApiError(NetworkError.SERIALIZATION, e.message))
    } catch (e: ConnectTimeoutException) {
        return Result.Error(ApiError(NetworkError.REQUEST_TIMEOUT, "Connection timeout"))
    } catch (e: SocketTimeoutException) {
        return Result.Error(ApiError(NetworkError.REQUEST_TIMEOUT, "Connection timeout"))
    } catch (e: Exception) {
        return Result.Error(ApiError(NetworkError.UNKNOWN, e.message))
    }
    return when (response.status.value) {
        in 200..299 -> {
            Result.Success(response.body<T>())
        }

        401 -> Result.Error(ApiError(NetworkError.UNAUTHORIZED))
        409 -> Result.Error(ApiError(NetworkError.CONFLICT))
        408 -> Result.Error(ApiError(NetworkError.REQUEST_TIMEOUT))
        413 -> Result.Error(ApiError(NetworkError.PAYLOAD_TOO_LARGE))
        in 500..599 -> {
            Result.Error(
                ApiError(
                    NetworkError.SERVER_ERROR,
                    Json.decodeFromString<ErrorResponse>(response.bodyAsText()).message
                )
            )
        }

        else -> {
            Result.Error(
                ApiError(
                    NetworkError.UNKNOWN,
                    Json.decodeFromString<ErrorResponse>(response.bodyAsText()).message
                )
            )
        }
    }
}