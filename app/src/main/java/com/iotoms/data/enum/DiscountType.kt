package com.iotoms.data.enum

/**
 * Created by Fasil on 29/11/2025
 */
enum class DiscountType(private val id: Int) {
    PERCENTAGE(1),
    AMOUNT(2);

    fun getId(): Int {
        return id
    }

    companion object {
        fun fromId(id: Int): DiscountType? {
            return entries.find { it.id == id }
        }
    }
}