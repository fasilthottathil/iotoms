package com.iotoms.ui.cart

import androidx.paging.PagingData
import com.iotoms.data.local.entity.ItemEntity

/**
 * Created by Fasil on 27/12/2025
 */
sealed class CartUiState {
    data object Loading : CartUiState()
    data class Error(val message: String) : CartUiState()
    data object Idle : CartUiState()
}