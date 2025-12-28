package com.iotoms.ui.cart

import com.iotoms.data.local.entity.CartEntity
import com.iotoms.data.local.entity.CartItemEntity

/**
 * Created by Fasil on 27/12/2025
 */
data class CartUiState(
    val isLoading: Boolean = false,
    val cart: CartState = CartState(
        cartItems = emptyList(),
        cartEntity = null,
        quickPicks = emptyList(),
        regInfo = null
    ),
    val errorMessage: String? = null
)

data class CartState(
    val cartItems: List<CartItemEntity>,
    val cartEntity: CartEntity?,
    val quickPicks: List<QuickPick> = emptyList(),
    val regInfo: RegisterInfo? = null
)

sealed interface ItemSource {
    data object All : ItemSource
    data class ByIds(var itemIds: List<String>, var pageId: String) : ItemSource
}

data class QuickPick(
    var id: String,
    var backgroundColor: String?,
    var itemIds: List<String>,
    var label: String
)

data class RegisterInfo(
    val id: Int?,
    val registerName: String?,
    val storeId: Int?,
    val storeName: String?,
    val venueId: Int?,
    val venueName: String?
)