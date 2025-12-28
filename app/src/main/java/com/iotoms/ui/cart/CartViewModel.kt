package com.iotoms.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.di.DispatcherProvider
import com.iotoms.domain.usecase.business.register.GetRegisterInfoUseCase
import com.iotoms.domain.usecase.cart.AddGeneralItemToCartUseCase
import com.iotoms.domain.usecase.cart.AddItemToCartUseCase
import com.iotoms.domain.usecase.cart.ClearCartUseCase
import com.iotoms.domain.usecase.cart.GetCartAsFlowUseCase
import com.iotoms.domain.usecase.cart.GetCartItemsUseCase
import com.iotoms.domain.usecase.cart.UpdateCartItemQuantityUseCase
import com.iotoms.domain.usecase.item.GetPaginatedItemsByItemIdsFromLocalUseCase
import com.iotoms.domain.usecase.item.GetPaginatedItemsFromLocalUseCase
import com.iotoms.domain.usecase.quickpick.GetQuickPickFromDbUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
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
    private val clearCartUseCase: ClearCartUseCase,
    private val getQuickPickFromDbUseCase: GetQuickPickFromDbUseCase,
    private val getPaginatedItemsByItemIdsFromLocalUseCase: GetPaginatedItemsByItemIdsFromLocalUseCase,
    private val getRegisterInfoUseCase: GetRegisterInfoUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<CartUiState>(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState

    private val _itemSource = MutableStateFlow<ItemSource>(ItemSource.All)
    val itemSource: StateFlow<ItemSource> = _itemSource


    @OptIn(ExperimentalCoroutinesApi::class)
    val pagingItemsFlow = _itemSource
        .flatMapLatest { source ->
            when (source) {
                ItemSource.All ->
                    getPaginatedItemsFromLocalUseCase()

                is ItemSource.ByIds ->
                    if (source.itemIds.isEmpty())
                        getPaginatedItemsFromLocalUseCase()
                    else
                        getPaginatedItemsByItemIdsFromLocalUseCase(source.itemIds)
            }
        }
        .cachedIn(viewModelScope)

    init {
        observeCartWithQuickPicks()
    }

    private fun observeCartWithQuickPicks() {
        viewModelScope.launch(dispatchers.io) {
            combine(
                getCartAsFlowUseCase(),
                getQuickPickFromDbUseCase(),
                getRegisterInfoUseCase()
            ) { cartEntity, quickPicks, regInfo ->

                if (cartEntity == null) {
                    CartUiState(
                        isLoading = false,
                        cart = CartState(
                            cartItems = emptyList(),
                            cartEntity = null,
                            quickPicks = quickPicks.map {
                                QuickPick(
                                    id = it.id,
                                    backgroundColor = it.backgroundColor,
                                    itemIds = it.buttons?.map { button -> button?.itemId.orEmpty() }
                                        ?: emptyList(),
                                    label = it.title.orEmpty()
                                )
                            },
                            regInfo = RegisterInfo(
                                id = regInfo.id,
                                registerName = regInfo.name,
                                storeId = regInfo.store?.id,
                                storeName = regInfo.store?.name,
                                venueId = regInfo.store?.venue?.id,
                                venueName = regInfo.store?.venue?.name
                            )
                        )
                    )
                } else {
                    val cartItems =
                        getCartItemsUseCase(cartEntity.transactionNumber)

                    CartUiState(
                        cart = CartState(
                            cartItems = cartItems,
                            cartEntity = cartEntity,
                            quickPicks = quickPicks.map {
                                QuickPick(
                                    id = it.id,
                                    backgroundColor = it.backgroundColor,
                                    itemIds = it.buttons?.map { button -> button?.itemId.orEmpty() }
                                        ?: emptyList(),
                                    label = it.title.orEmpty()
                                )
                            },
                            regInfo = RegisterInfo(
                                id = regInfo.id,
                                registerName = regInfo.name,
                                storeId = regInfo.store?.id,
                                storeName = regInfo.store?.name,
                                venueId = regInfo.store?.venue?.id,
                                venueName = regInfo.store?.venue?.name
                            )
                        )
                    )
                }
            }.collectLatest { state ->
                _uiState.update { state }
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
                _uiState.update { it.copy(errorMessage = "") }
                delay(100)
                _uiState.update { it.copy(errorMessage = e.message ?: "Unknown Error") }
            }
        }
    }

    fun addGeneralItemToCart(amount: String) {
        viewModelScope.launch(dispatchers.io) {
            runCatching {
                addGeneralItemToCartUseCase.invoke("General Item", amount.toDouble())
            }.onFailure { e ->
                _uiState.update { it.copy(errorMessage = "") }
                delay(100)
                _uiState.update { it.copy(errorMessage = e.message ?: "Unknown Error") }
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch(dispatchers.io) {
            clearCartUseCase()
        }
    }

    fun setItemSource(itemSource: ItemSource) {
        _itemSource.update { itemSource }
    }

}