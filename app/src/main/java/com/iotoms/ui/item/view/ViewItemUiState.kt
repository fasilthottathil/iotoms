package com.iotoms.ui.item.view

import com.iotoms.data.local.entity.ItemEntity

/**
 * Created by Fasil on 01/01/2026
 */
sealed class ViewItemUiState {
    data object Idle: ViewItemUiState()
    data class Data(val itemEntity: ItemEntity?): ViewItemUiState()
}