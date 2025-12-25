package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.TaxEntity

/**
 * Created by Fasil on 25/12/2025
 */
@Dao
interface TaxDao {
    @Upsert
    suspend fun insertTaxes(taxEntity: List<TaxEntity>)

    @Query("SELECT * FROM taxes")
    suspend fun getAllTaxes(): List<TaxEntity>

    @Query("SELECT * FROM taxes WHERE id = :taxId LIMIT 1")
    suspend fun getTaxById(taxId: Int): TaxEntity?

    @Delete
    suspend fun deleteTax(taxEntity: TaxEntity)

    @Query("DELETE FROM taxes")
    suspend fun clearTaxes()

    @Query("DELETE FROM taxes WHERE id = :taxId")
    suspend fun deleteTaxById(taxId: Int)

}