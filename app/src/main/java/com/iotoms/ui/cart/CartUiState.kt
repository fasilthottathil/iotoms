package com.iotoms.ui.cart

import androidx.paging.PagingData
import com.iotoms.data.local.entity.CartEntity
import com.iotoms.data.local.entity.CartItemEntity
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.local.entity.QuickPickEntity
import com.iotoms.data.model.response.QuickPickButtonsItem

/**
 * Created by Fasil on 27/12/2025
 */
sealed class CartUiState {
    data object Loading : CartUiState()
    data class Cart(
        val cartItems: List<CartItemEntity>,
        val cartEntity: CartEntity?,
        val quickPicks: List<QuickPick> = emptyList()
    ) : CartUiState()
    data class Error(val message: String) : CartUiState()
    data object Idle : CartUiState()
}

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