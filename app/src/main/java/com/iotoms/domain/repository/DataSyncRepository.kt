package com.iotoms.domain.repository

/**
 * Created by Fasil on 23/11/2025
 */
interface DataSyncRepository {
    suspend fun doSync()
}