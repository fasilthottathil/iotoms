package com.iotoms.ui.item.search

/**
 * Created by Fasil on 29/12/2025
 */
sealed class SearchItemUiState {
    data object Idle: SearchItemUiState()
    data object ItemAdded: SearchItemUiState()
}