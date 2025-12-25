package com.iotoms.data.repository

import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.model.response.TaxResponse
import com.iotoms.data.remote.api.ApiError
import com.iotoms.data.remote.api.apiRequest
import com.iotoms.domain.repository.TaxRepository
import com.iotoms.utils.Result
import com.iotoms.utils.constants.ApiUrl
import com.iotoms.utils.extensions.getOrZero
import io.ktor.client.HttpClient
import io.ktor.client.request.get

/**
 * Created by Fasil on 20/12/2025
 */
class TaxRepositoryImpl(
    private val client: HttpClient,
    private val appDatabase: AppDatabase
): TaxRepository {
    override suspend fun getAllTaxes(): Result<List<TaxResponse>, ApiError> {
        return apiRequest<List<TaxResponse>> {
            client.get(ApiUrl.TAXES)
        }
    }

    override suspend fun calculateTax(taxId: Int, itemEntity: ItemEntity): Double {
        val tax = appDatabase.taxDao().getTaxById(taxId)
        if (tax == null) {
            return 0.0
        } else {
            val taxRate1 = tax.taxRate1.getOrZero()
            val taxRate2 = tax.taxRate2.getOrZero()

            val price = itemEntity.sellingPrice.getOrZero()
            val taxAmount1 = (price * taxRate1) / 100
            val taxAmount2 = (price * taxRate2) / 100
            return taxAmount1 + taxAmount2
        }
    }
}