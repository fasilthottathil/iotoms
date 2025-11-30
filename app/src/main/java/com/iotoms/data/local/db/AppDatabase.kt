package com.iotoms.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iotoms.data.local.dao.CartDao
import com.iotoms.data.local.entity.CartItemEntity

/**
 * Created by Fasil on 29/11/2025
 */
@Database(
    entities = [CartItemEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}