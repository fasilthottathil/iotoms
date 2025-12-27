package com.iotoms.domain.repository

import com.iotoms.data.enum.SyncStatus
import kotlinx.coroutines.flow.Flow

/**
 * Created by Fasil on 23/11/2025
 */
interface DataSyncRepository {
    suspend fun doSync(): Flow<Triple<SyncStatus, Float, Boolean>>
}