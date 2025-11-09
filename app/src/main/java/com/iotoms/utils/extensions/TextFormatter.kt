package com.iotoms.utils.extensions

import java.util.Locale

/**
 * Created by Fasil on 07/11/2025
 */

/**
 * Formats a string representing a monetary amount by ensuring two decimal places.
 * Non-digit characters are removed before formatting.
 *
 * Examples:
 * - "5" -> "0.05"
 * - "50" -> "0.50"
 * - "500" -> "5.00"
 * - "12345" -> "123.45"
 *
 * @return A formatted string with two decimal places.
 */
fun String.formatAmount(): String {
    val digitsOnly = this.filter { it.isDigit() }

    // Handle empty string
    if (digitsOnly.isEmpty()) {
        return "0.00"
    }

    // Pad with leading zeros if necessary (minimum 3 digits for proper decimal placement)
    val paddedDigits = digitsOnly.padStart(3, '0')

    // Split into dollars and cents (last 2 digits are cents)
    val cents = paddedDigits.takeLast(2)
    val dollars = paddedDigits.dropLast(2).toLongOrNull()?.toString() ?: "0"

    return "$dollars.$cents"
}