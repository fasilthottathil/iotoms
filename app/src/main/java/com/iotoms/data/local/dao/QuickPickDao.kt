package com.iotoms.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.iotoms.data.local.entity.QuickPickEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 28/12/2025
 */
@Dao
interface QuickPickDao {
    @Upsert
    suspend fun insertQuickPicks(quickPickEntities: List<QuickPickEntity>)

    @Query("SELECT * FROM quick_picks ORDER BY pageIndex ASC")
    fun getAllQuickPicks(): Flow<List<QuickPickEntity>>

    @Query("DELETE FROM quick_picks")
    suspend fun clearQuickPicks()
}