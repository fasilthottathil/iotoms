package com.iotoms.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Fasil on 29/11/2025
 */

fun Any.toStringByGson(): String = Gson().toJson(this)

inline fun <reified T, reified O> T.mapObject(): O? {
    val type = object : TypeToken<O>() {}.type
    return try {
        Gson().fromJson(this?.toStringByGson(), type)
    } catch (e: Exception) {
        null
    }
}

inline fun <reified O> String.mapObjectFromJsonString(): O? {
    val type = object : TypeToken<O>() {}.type
    return try {
        Gson().fromJson(this, type)
    } catch (e: Exception) {
        null
    }
}