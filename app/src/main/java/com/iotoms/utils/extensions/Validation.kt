package com.iotoms.utils.extensions

/**
 * Created by Fasil on 20/12/2025
 */
fun Int?.getOrZero(): Int {
    return this ?: 0
}

fun Double?.getOrZero(): Double {
    return this ?: 0.0
}