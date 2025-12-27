package com.iotoms.ui.sync

/**
 * Created by Fasil on 26/12/2025
 */
sealed class DataSyncUiState {
    data object Idle : DataSyncUiState()
    data object Loading : DataSyncUiState()
    data class Progress(val progress: Float = 0f) : DataSyncUiState()
    data object SyncSuccess : DataSyncUiState()
}