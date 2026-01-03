package com.iotoms.domain.repository

import com.iotoms.data.local.entity.BrandEntity
import com.iotoms.data.local.entity.CategoryEntity
import com.iotoms.data.local.entity.ColorEntity
import com.iotoms.data.local.entity.DepartmentEntity
import com.iotoms.data.local.entity.SizeEntity
import com.iotoms.data.local.entity.StyleEntity
import com.iotoms.data.local.entity.SubCategoryEntity
import com.iotoms.data.model.response.AttributeResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 23/11/2025
 */
interface AttributeRepository {
    suspend fun getDepartments(): Result<List<AttributeResponse>, ApiError>
    suspend fun getColors(): Result<List<AttributeResponse>, ApiError>
    suspend fun getSizes(): Result<List<AttributeResponse>, ApiError>
    suspend fun getCategories(): Result<List<AttributeResponse>, ApiError>
    suspend fun getSubCategories(): Result<List<AttributeResponse>, ApiError>
    suspend fun getBrands(): Result<List<AttributeResponse>, ApiError>
    suspend fun getStyles(): Result<List<AttributeResponse>, ApiError>
    fun getDepartmentsFromLocal(): Flow<List<DepartmentEntity>>
    fun getColorsFromLocal(): Flow<List<ColorEntity>>
    fun getSizesFromLocal(): Flow<List<SizeEntity>>
    fun getCategoriesFromLocal(): Flow<List<CategoryEntity>>
    fun getSubCategoriesFromLocal(): Flow<List<SubCategoryEntity>>
    fun getBrandsFromLocal(): Flow<List<BrandEntity>>
    fun getStylesFromLocal(): Flow<List<StyleEntity>>

}