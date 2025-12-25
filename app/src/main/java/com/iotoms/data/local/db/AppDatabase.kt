package com.iotoms.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iotoms.data.local.dao.AttributeDao
import com.iotoms.data.local.dao.CartDao
import com.iotoms.data.local.dao.ItemDao
import com.iotoms.data.local.dao.TaxDao
import com.iotoms.data.local.entity.CartEntity
import com.iotoms.data.local.entity.CartItemEntity

/**
 * Created by Fasil on 29/11/2025
 */
@Database(
    entities = [CartEntity::class, CartItemEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun taxDao(): TaxDao
    abstract fun itemDao(): ItemDao
    abstract fun attributeDao(): AttributeDao
}