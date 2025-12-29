package com.iotoms.ui.item.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.di.DispatcherProvider
import com.iotoms.domain.usecase.cart.AddItemToCartUseCase
import com.iotoms.domain.usecase.item.SearchItemFromLocalUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by Fasil on 29/12/2025
 */
class SearchItemViewModel(
    private val dispatchers: DispatcherProvider,
    val searchItemFromLocalUseCase: SearchItemFromLocalUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchItemUiState>(SearchItemUiState.Idle)
    val uiState: StateFlow<SearchItemUiState> = _uiState
    private val searchQuery = MutableStateFlow<String?>(null)

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pagingItemsFlow = searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            searchItemFromLocalUseCase(query.orEmpty())
        }
        .cachedIn(viewModelScope)

    fun onSearch(query: String) {
        searchQuery.update { query.takeIf { it.isNotBlank() } }
    }

    fun addItemToCart(itemEntity: ItemEntity) {
        viewModelScope.launch(dispatchers.io) {
            addItemToCartUseCase.invoke(itemEntity)
            _uiState.update { SearchItemUiState.ItemAdded }
        }
    }

}