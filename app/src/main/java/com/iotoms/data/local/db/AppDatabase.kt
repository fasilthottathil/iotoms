package com.iotoms.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iotoms.data.local.dao.AttributeDao
import com.iotoms.data.local.dao.BrandingDao
import com.iotoms.data.local.dao.CartDao
import com.iotoms.data.local.dao.CustomerDao
import com.iotoms.data.local.dao.ItemDao
import com.iotoms.data.local.dao.PlanDao
import com.iotoms.data.local.dao.RegisterDao
import com.iotoms.data.local.dao.StoreDao
import com.iotoms.data.local.dao.TaxDao
import com.iotoms.data.local.dao.UserDao
import com.iotoms.data.local.dao.VenueDao
import com.iotoms.data.local.entity.BrandEntity
import com.iotoms.data.local.entity.BrandingEntity
import com.iotoms.data.local.entity.CartEntity
import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.data.local.entity.CategoryEntity
import com.iotoms.data.local.entity.ColorEntity
import com.iotoms.data.local.entity.CustomerEntity
import com.iotoms.data.local.entity.DepartmentEntity
import com.iotoms.data.local.entity.DiscountEntity
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.local.entity.PlanEntity
import com.iotoms.data.local.entity.RegisterEntity
import com.iotoms.data.local.entity.SizeEntity
import com.iotoms.data.local.entity.StoreEntity
import com.iotoms.data.local.entity.StyleEntity
import com.iotoms.data.local.entity.SubCategoryEntity
import com.iotoms.data.local.entity.TaxEntity
import com.iotoms.data.local.entity.UserEntity
import com.iotoms.data.local.entity.VenueEntity

/**
 * Created by Fasil on 29/11/2025
 */
@Database(
    entities = [
        CartEntity::class, CartItemEntity::class, BrandEntity::class, CategoryEntity::class,
        ColorEntity::class, DepartmentEntity::class, DiscountEntity::class, ItemEntity::class,
        SizeEntity::class, StyleEntity::class, SubCategoryEntity::class, TaxEntity::class,
        UserEntity::class, CustomerEntity::class, PlanEntity::class, VenueEntity::class,
        StoreEntity::class, BrandingEntity::class, RegisterEntity::class
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
    abstract fun customerDao(): CustomerDao
    abstract fun planDao(): PlanDao
    abstract fun venueDao(): VenueDao
    abstract fun storeDao(): StoreDao
    abstract fun brandingDao(): BrandingDao
    abstract fun registerDao(): RegisterDao
}