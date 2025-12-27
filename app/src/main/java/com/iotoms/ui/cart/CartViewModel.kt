package com.iotoms.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.iotoms.domain.usecase.item.GetPaginatedItemsFromLocalUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Fasil on 27/12/2025
 */
class CartViewModel(
    private val getPaginatedItemsFromLocalUseCase: GetPaginatedItemsFromLocalUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<CartUiState>(CartUiState.Idle)
    val uiState: StateFlow<CartUiState> = _uiState

    val pagingItemsFlow =
        getPaginatedItemsFromLocalUseCase()
            .cachedIn(viewModelScope)

}