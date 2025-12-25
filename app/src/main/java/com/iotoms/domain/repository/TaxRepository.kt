package com.iotoms.domain.repository

import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.model.response.TaxResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.utils.Result

/**
 * Created by Fasil on 20/12/2025
 */
interface TaxRepository {
    suspend fun getAllTaxes(): Result<List<TaxResponse>, ApiError>
    suspend fun calculateTax(taxId: Int, itemEntity: ItemEntity): Double
}