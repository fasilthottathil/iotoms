package com.iotoms.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.iotoms.data.local.entity.CartEntity
import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.di.DispatcherProvider
import com.iotoms.domain.usecase.cart.AddGeneralItemToCartUseCase
import com.iotoms.domain.usecase.cart.AddItemToCartUseCase
import com.iotoms.domain.usecase.cart.ClearCartUseCase
import com.iotoms.domain.usecase.cart.GetCartAsFlowUseCase
import com.iotoms.domain.usecase.cart.GetCartItemsUseCase
import com.iotoms.domain.usecase.cart.UpdateCartItemQuantityUseCase
import com.iotoms.domain.usecase.item.GetPaginatedItemsFromLocalUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by Fasil on 27/12/2025
 */
class CartViewModel(
    private val dispatchers: DispatcherProvider,
    getPaginatedItemsFromLocalUseCase: GetPaginatedItemsFromLocalUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val getCartAsFlowUseCase: GetCartAsFlowUseCase,
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val updateCartItemQuantityUseCase: UpdateCartItemQuantityUseCase,
    private val addGeneralItemToCartUseCase: AddGeneralItemToCartUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<CartUiState>(CartUiState.Idle)
    val uiState: StateFlow<CartUiState> = _uiState

    val pagingItemsFlow = getPaginatedItemsFromLocalUseCase().cachedIn(viewModelScope)

    init {
        observeCart()
    }

    private fun observeCart() {
        viewModelScope.launch(dispatchers.io) {
            getCartAsFlowUseCase().collectLatest { cartEntity ->
                if (cartEntity == null) {
                    _uiState.update { CartUiState.Cart(cartItems = emptyList(), cartEntity = null) }
                } else {
                    val cartItems = getCartItemsUseCase(cartEntity.transactionNumber)
                    _uiState.update {
                        CartUiState.Cart(
                            cartItems = cartItems,
                            cartEntity = cartEntity
                        )
                    }
                }
            }
        }
    }

    fun addItemToCart(itemEntity: ItemEntity) {
        viewModelScope.launch(dispatchers.io) {
            addItemToCartUseCase.invoke(itemEntity)
        }
    }

    fun updateQuantity(cartItemEntity: CartItemEntity) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                updateCartItemQuantityUseCase.invoke(cartItemEntity)
            }.onFailure { e ->
                _uiState.update { CartUiState.Error("") }
                delay(100)
                _uiState.update { CartUiState.Error(e.message ?: "Unknown Error") }
            }
        }
    }

    fun addGeneralItemToCart(amount: String) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                addGeneralItemToCartUseCase.invoke("General Item", amount.toDouble())
            }.onFailure { e ->
                _uiState.update { CartUiState.Error("") }
                delay(100)
                _uiState.update { CartUiState.Error(e.message ?: "Unknown Error") }
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch(dispatchers.io) {
            clearCartUseCase()
        }
    }

}