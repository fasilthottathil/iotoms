package com.iotoms.data.worker

import android.content.Context
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.iotoms.R
import com.iotoms.data.enum.SyncStatus
import com.iotoms.domain.usecase.sync.DataSyncUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Created by Fasil on 23/11/2025
 */
class DataSyncWorker(
    appContext: Context,
    params: WorkerParameters,
    private val dataSyncUseCase: DataSyncUseCase
) : CoroutineWorker(appContext = appContext, params = params) {

    companion object {
        const val WORK_NAME = "DATA_SYNC_WORK"
        const val SYNC_PROGRESS = "sync_progress"
        const val SYNC_NOTIFICATION_ID = 101
    }

    override suspend fun doWork(): Result {

        val notification = NotificationCompat.Builder(applicationContext, "sync")
            .setContentTitle("Sync in progress")
            .setContentText("Syncing data…")
            .setSmallIcon(R.drawable.ic_sync)
            .setOngoing(true)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            setForeground(ForegroundInfo(SYNC_NOTIFICATION_ID, notification, FOREGROUND_SERVICE_TYPE_DATA_SYNC))
        } else {
            setForeground(ForegroundInfo(SYNC_NOTIFICATION_ID, notification))
        }

        return suspendCancellableCoroutine { continuation ->
            val job = CoroutineScope(Dispatchers.IO).launch {
                dataSyncUseCase().collectLatest {
                    when (it.first) {
                        SyncStatus.FAILED -> {
                            continuation.resume(Result.failure())
                            cancel() // stop collecting
                        }

                        SyncStatus.COMPLETED -> {
                            continuation.resume(Result.success())
                            cancel()
                        }

                        SyncStatus.IN_PROGRESS -> {
                            setProgress(Data.Builder().putFloat(SYNC_PROGRESS, it.second).build())
                        }
                    }
                }
            }

            continuation.invokeOnCancellation {
                job.cancel()
            }
        }
    }
}