package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.iotoms.data.local.entity.BrandEntity
import com.iotoms.data.local.entity.CategoryEntity
import com.iotoms.data.local.entity.ColorEntity
import com.iotoms.data.local.entity.DepartmentEntity
import com.iotoms.data.local.entity.SizeEntity
import com.iotoms.data.local.entity.StyleEntity
import com.iotoms.data.local.entity.SubCategoryEntity

/**
 * Created by Fasil on 25/12/2025
 */
@Dao
interface AttributeDao {
    @Upsert
    suspend fun insertDepartments(departmentEntities: List<DepartmentEntity>)

    @Upsert
    suspend fun insertColors(colorEntities: List<ColorEntity>)

    @Upsert
    suspend fun insertSizes(sizeEntities: List<SizeEntity>)

    @Upsert
    suspend fun insertStyles(styleEntities: List<StyleEntity>)

    @Upsert
    suspend fun insertCategories(categoryEntities: List<CategoryEntity>)

    @Upsert
    suspend fun insertSubCategories(subCategoryEntities: List<SubCategoryEntity>)

    @Upsert
    suspend fun insertBrands(brandEntities: List<BrandEntity>)




}