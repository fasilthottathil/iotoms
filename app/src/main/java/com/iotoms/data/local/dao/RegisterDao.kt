package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.RegisterEntity

/**
 * Created by Fasil on 27/12/2025
 */
@Dao
interface RegisterDao {
    @Upsert
    suspend fun insertRegisters(registers: List<RegisterEntity>)

    @Query("SELECT * FROM registers")
    suspend fun getAllRegisters(): List<RegisterEntity>

    @Query("DELETE FROM registers")
    suspend fun deleteAllRegisters()

    @Query("SELECT * FROM registers WHERE id = :id")
    suspend fun getRegisterById(id: Int): RegisterEntity?

    @Query("DELETE FROM registers WHERE id = :id")
    suspend fun deleteRegisterById(id: Int)

}