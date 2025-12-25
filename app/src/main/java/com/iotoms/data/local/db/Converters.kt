package com.iotoms.data.local.db

import androidx.room.TypeConverter
import com.iotoms.data.model.CartDiscount
import com.iotoms.data.model.response.Brand
import com.iotoms.data.model.response.Color
import com.iotoms.data.model.response.ImageGallery
import com.iotoms.data.model.response.Size
import com.iotoms.data.model.response.Style
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

    @TypeConverter
    fun fromIntList(value: List<Int>?): String? {
        return value?.toStringByGson()
    }

    @TypeConverter
    fun toIntList(value: String?): List<Int>? {
        return value?.mapObjectFromJsonString()
    }

    @TypeConverter
    fun fromImageGallery(value: ImageGallery?): String? {
        return value?.toStringByGson()
    }

    @TypeConverter
    fun toImageGallery(value: String?): ImageGallery? {
        return value?.mapObjectFromJsonString()
    }

    @TypeConverter
    fun fromColor(value: Color?): String? {
        return value?.toStringByGson()
    }

    @TypeConverter
    fun toColor(value: String?): Color? {
        return value?.mapObjectFromJsonString()
    }

    // ----- Brand -----
    @TypeConverter
    fun fromBrand(value: Brand?): String? {
        return value?.toStringByGson()
    }

    @TypeConverter
    fun toBrand(value: String?): Brand? {
        return value?.mapObjectFromJsonString()
    }

    // ----- Style -----
    @TypeConverter
    fun fromStyle(value: Style?): String? {
        return value?.toStringByGson()
    }

    @TypeConverter
    fun toStyle(value: String?): Style? {
        return value?.mapObjectFromJsonString()
    }

    // ----- Size -----
    @TypeConverter
    fun fromSize(value: Size?): String? {
        return value?.toStringByGson()
    }

    @TypeConverter
    fun toSize(value: String?): Size? {
        return value?.mapObjectFromJsonString()
    }

}