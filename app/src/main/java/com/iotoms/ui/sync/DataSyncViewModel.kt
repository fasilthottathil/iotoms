package com.iotoms.ui.sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.BackoffPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.iotoms.data.worker.DataSyncWorker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/**
 * Created by Fasil on 26/12/2025
 */
class DataSyncViewModel(private val workManager: WorkManager) : ViewModel() {
    private val _uiState = MutableStateFlow<DataSyncUiState>(DataSyncUiState.Idle)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DataSyncUiState.Idle
    )

    init {
        viewModelScope.launch {
            doSync()
            workManager.getWorkInfosForUniqueWorkFlow(DataSyncWorker.WORK_NAME).collectLatest {
                it.firstOrNull()?.let { workInfo ->
                    if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                        _uiState.update { DataSyncUiState.SyncSuccess }
                    }
                    if (workInfo.state == WorkInfo.State.RUNNING) {
                        val progress = workInfo.progress.getFloat(DataSyncWorker.SYNC_PROGRESS, 0f)
                        _uiState.update { DataSyncUiState.Progress(progress) }
                    }
                }
            }
        }
    }

    fun doSync() {
        val request = OneTimeWorkRequestBuilder<DataSyncWorker>()
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                30,
                TimeUnit.SECONDS
            )
            .build()

        workManager.enqueueUniqueWork(
            DataSyncWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }


}