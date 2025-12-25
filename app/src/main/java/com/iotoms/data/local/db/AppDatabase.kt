package com.iotoms.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iotoms.data.local.dao.AttributeDao
import com.iotoms.data.local.dao.CartDao
import com.iotoms.data.local.dao.ItemDao
import com.iotoms.data.local.dao.TaxDao
import com.iotoms.data.local.dao.UserDao
import com.iotoms.data.local.entity.BrandEntity
import com.iotoms.data.local.entity.CartEntity
import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.data.local.entity.CategoryEntity
import com.iotoms.data.local.entity.ColorEntity
import com.iotoms.data.local.entity.DepartmentEntity
import com.iotoms.data.local.entity.DiscountEntity
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.local.entity.SizeEntity
import com.iotoms.data.local.entity.StyleEntity
import com.iotoms.data.local.entity.SubCategoryEntity
import com.iotoms.data.local.entity.TaxEntity
import com.iotoms.data.local.entity.UserEntity

/**
 * Created by Fasil on 29/11/2025
 */
@Database(
    entities = [
        CartEntity::class, CartItemEntity::class, BrandEntity::class, CategoryEntity::class,
        ColorEntity::class, DepartmentEntity::class, DiscountEntity::class, ItemEntity::class,
        SizeEntity::class, StyleEntity::class, SubCategoryEntity::class, TaxEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun taxDao(): TaxDao
    abstract fun itemDao(): ItemDao
    abstract fun attributeDao(): AttributeDao
    abstract fun userDao(): UserDao
}