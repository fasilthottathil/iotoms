package com.iotoms.data.repository

import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.local.entity.BrandEntity
import com.iotoms.data.local.entity.CategoryEntity
import com.iotoms.data.local.entity.ColorEntity
import com.iotoms.data.local.entity.DepartmentEntity
import com.iotoms.data.local.entity.SizeEntity
import com.iotoms.data.local.entity.StyleEntity
import com.iotoms.data.local.entity.SubCategoryEntity
import com.iotoms.data.mapper.toBrandEntity
import com.iotoms.data.mapper.toCategoryEntity
import com.iotoms.data.mapper.toColorEntity
import com.iotoms.data.mapper.toDepartmentEntity
import com.iotoms.data.mapper.toSizeEntity
import com.iotoms.data.mapper.toStyleEntity
import com.iotoms.data.mapper.toSubCategoryEntity
import com.iotoms.data.model.response.AttributeResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.AttributeRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 23/11/2025
 */
class AttributeRepositoryImpl(
    private val client: HttpClient,
    private val appDatabase: AppDatabase
) : AttributeRepository {
    override suspend fun getDepartments(): Result<List<AttributeResponse>, ApiError> {
        return apiRequest<List<AttributeResponse>> {
            client.get(ApiUrl.DEPARTMENTS)
        }.also {
            if (it is Result.Success) {
                it.data.map { response -> response.toDepartmentEntity() }.also { entities ->
                    appDatabase.attributeDao().clearDepartments()
                    appDatabase.attributeDao().insertDepartments(entities)
                }
            }
        }
    }

    override suspend fun getColors(): Result<List<AttributeResponse>, ApiError> {
        return apiRequest<List<AttributeResponse>> {
            client.get(ApiUrl.COLORS)
        }.also {
            if (it is Result.Success) {
                it.data.map { response -> response.toColorEntity() }.also { entities ->
                    appDatabase.attributeDao().clearColors()
                    appDatabase.attributeDao().insertColors(entities)
                }
            }
        }
    }

    override suspend fun getSizes(): Result<List<AttributeResponse>, ApiError> {
        return apiRequest<List<AttributeResponse>> {
            client.get(ApiUrl.SIZES)
        }.also {
            if (it is Result.Success) {
                it.data.map { response -> response.toSizeEntity() }.also { entities ->
                    appDatabase.attributeDao().insertSizes(entities)
                }
            }
        }
    }

    override suspend fun getCategories(): Result<List<AttributeResponse>, ApiError> {
        return apiRequest<List<AttributeResponse>> {
            client.get(ApiUrl.CATEGORIES)
        }.also {
            if (it is Result.Success) {
                it.data.map { response -> response.toCategoryEntity() }.also { entities ->
                    appDatabase.attributeDao().clearCategories()
                    appDatabase.attributeDao().insertCategories(entities)
                }
            }
        }
    }

    override suspend fun getSubCategories(): Result<List<AttributeResponse>, ApiError> {
        return apiRequest<List<AttributeResponse>> {
            client.get(ApiUrl.SUBCATEGORIES)
        }.also {
            if (it is Result.Success) {
                it.data.map { response -> response.toSubCategoryEntity() }.also { entities ->
                    appDatabase.attributeDao().clearSubCategories()
                    appDatabase.attributeDao().insertSubCategories(entities)
                }
            }
        }
    }

    override suspend fun getBrands(): Result<List<AttributeResponse>, ApiError> {
        return apiRequest<List<AttributeResponse>> {
            client.get(ApiUrl.BRANDS)
        }.also {
            if (it is Result.Success) {
                it.data.map { response -> response.toBrandEntity() }.also { entities ->
                    appDatabase.attributeDao().insertBrands(entities)
                }
            }
        }
    }

    override suspend fun getStyles(): Result<List<AttributeResponse>, ApiError> {
        return apiRequest<List<AttributeResponse>> {
            client.get(ApiUrl.STYLES)
        }.also {
            if (it is Result.Success) {
                it.data.map { response -> response.toStyleEntity() }.also { entities ->
                    appDatabase.attributeDao().clearStyles()
                    appDatabase.attributeDao().insertStyles(entities)
                }
            }
        }
    }

    override fun getDepartmentsFromLocal(): Flow<List<DepartmentEntity>> {
        return appDatabase.attributeDao().getDepartments()
    }

    override fun getColorsFromLocal(): Flow<List<ColorEntity>> {
        return appDatabase.attributeDao().getColors()
    }

    override fun getSizesFromLocal(): Flow<List<SizeEntity>> {
        return appDatabase.attributeDao().getSizes()
    }

    override fun getCategoriesFromLocal(): Flow<List<CategoryEntity>> {
        return appDatabase.attributeDao().getCategories()
    }

    override fun getSubCategoriesFromLocal(): Flow<List<SubCategoryEntity>> {
        return appDatabase.attributeDao().getSubCategories()
    }

    override fun getBrandsFromLocal(): Flow<List<BrandEntity>> {
        return appDatabase.attributeDao().getBrands()
    }

    override fun getStylesFromLocal(): Flow<List<StyleEntity>> {
        return appDatabase.attributeDao().getStyles()
    }
}