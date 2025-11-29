package com.iotoms.data.local.db

import androidx.room.TypeConverter
import com.iotoms.data.model.CartDiscount
import com.iotoms.utils.extensions.mapObjectFromJsonString
import com.iotoms.utils.extensions.toStringByGson

/**
 * Created by Fasil on 29/11/2025
 */
object Converters {

    @TypeConverter
    fun stringToList(value: String?): List<String>? {
        return value?.mapObjectFromJsonString<List<String>>()
    }

    @TypeConverter
    fun listToString(list: List<String>?): String? {
        return list?.toStringByGson()
    }

    @TypeConverter
    fun stringToCartDiscountList(value: String?): List<CartDiscount>? {
        return value?.mapObjectFromJsonString<List<CartDiscount>>()
    }

    @TypeConverter
    fun cartDiscountListToString(list: List<CartDiscount>?): String? {
        return list?.toStringByGson()
    }
}