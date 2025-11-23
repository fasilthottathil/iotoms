package com.iotoms.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.iotoms.domain.repository.DataSyncRepository

/**
 * Created by Fasil on 23/11/2025
 */
class DataSyncWorker(
    appContext: Context,
    params: WorkerParameters,
    private val dataSyncRepository: DataSyncRepository
) : CoroutineWorker(appContext = appContext, params = params) {
    override suspend fun doWork(): Result {
        dataSyncRepository.doSync()
        return Result.success()
    }
}