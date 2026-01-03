package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.BrandEntity
import com.iotoms.data.local.entity.CategoryEntity
import com.iotoms.data.local.entity.ColorEntity
import com.iotoms.data.local.entity.DepartmentEntity
import com.iotoms.data.local.entity.SizeEntity
import com.iotoms.data.local.entity.StyleEntity
import com.iotoms.data.local.entity.SubCategoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 25/12/2025
 */
@Dao
interface AttributeDao {
    @Upsert
    suspend fun insertDepartments(departmentEntities: List<DepartmentEntity>)

    @Query("DELETE FROM departments")
    suspend fun clearDepartments()

    @Query("SELECT * FROM departments WHERE status = 'ACTIVE'")
    fun getDepartments(): Flow<List<DepartmentEntity>>

    @Upsert
    suspend fun insertColors(colorEntities: List<ColorEntity>)

    @Query("DELETE FROM colors")
    suspend fun clearColors()

    @Query("SELECT * FROM colors WHERE status = 'ACTIVE'")
    fun getColors(): Flow<List<ColorEntity>>

    @Upsert
    suspend fun insertSizes(sizeEntities: List<SizeEntity>)

    @Query("DELETE FROM sizes")
    suspend fun clearSizes()

    @Query("SELECT * FROM sizes WHERE status = 'ACTIVE'")
    fun getSizes(): Flow<List<SizeEntity>>

    @Upsert
    suspend fun insertStyles(styleEntities: List<StyleEntity>)

    @Query("DELETE FROM styles")
    suspend fun clearStyles()

    @Query("SELECT * FROM styles WHERE status = 'ACTIVE'")
    fun getStyles(): Flow<List<StyleEntity>>

    @Upsert
    suspend fun insertCategories(categoryEntities: List<CategoryEntity>)

    @Query("DELETE FROM categories")
    suspend fun clearCategories()

    @Query("SELECT * FROM categories WHERE status = 'ACTIVE'")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Upsert
    suspend fun insertSubCategories(subCategoryEntities: List<SubCategoryEntity>)

    @Query("DELETE FROM sub_categories")
    suspend fun clearSubCategories()

    @Query("SELECT * FROM sub_categories WHERE status = 'ACTIVE'")
    fun getSubCategories(): Flow<List<SubCategoryEntity>>

    @Upsert
    suspend fun insertBrands(brandEntities: List<BrandEntity>)

    @Query("DELETE FROM brands")
    suspend fun clearBrands()

    @Query("SELECT * FROM brands WHERE status = 'ACTIVE'")
    fun getBrands(): Flow<List<BrandEntity>>


}