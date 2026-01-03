package com.iotoms.ui.item.attribute

/**
 * Created by Fasil on 03/01/2026
 */
sealed class AttributeScreenUiState {
    data object Idle: AttributeScreenUiState()
    data class Data(val list: List<Pair<Int, String>>): AttributeScreenUiState()
}