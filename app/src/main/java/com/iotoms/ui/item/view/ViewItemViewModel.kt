package com.iotoms.ui.item.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iotoms.di.DispatcherProvider
import com.iotoms.domain.usecase.item.GetItemByItemIdFlowUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by Fasil on 01/01/2026
 */
class ViewItemViewModel(
    private val dispatchers: DispatcherProvider,
    private val getItemByItemIdFlowUseCase: GetItemByItemIdFlowUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ViewItemUiState>(ViewItemUiState.Idle)
    val uiState: StateFlow<ViewItemUiState> = _uiState

    fun getItemByItemId(itemId: String) {
        viewModelScope.launch(dispatchers.io) {
            getItemByItemIdFlowUseCase(itemId).collectLatest { value ->
                _uiState.update { ViewItemUiState.Data(value) }
            }
        }
    }
}