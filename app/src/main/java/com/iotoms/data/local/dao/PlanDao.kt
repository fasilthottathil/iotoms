package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.PlanEntity

/**
 * Created by Fasil on 26/12/2025
 */
@Dao
interface PlanDao {
    @Upsert
    suspend fun insertPlans(plans: List<PlanEntity>)

    @Query("SELECT * FROM plans")
    suspend fun getAllPlans(): List<PlanEntity>

    @Query("DELETE FROM plans")
    suspend fun deleteAllPlans()

    @Query("SELECT * FROM plans WHERE code = :code")
    suspend fun getPlanByCode(code: String): PlanEntity?

    @Query("DELETE FROM plans WHERE code = :code")
    suspend fun deletePlanByCode(code: String)

}