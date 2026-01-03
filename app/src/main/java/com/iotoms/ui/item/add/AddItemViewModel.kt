package com.iotoms.ui.item.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.data.model.response.Color
import com.iotoms.di.DispatcherProvider
import com.iotoms.ui.item.attribute.AttributeScreenUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.map

/**
 * Created by Fasil on 03/01/2026
 */
class AddItemViewModel(
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddItemScreenUiState())
    val uiState: StateFlow<AddItemScreenUiState> = _uiState

    fun setAttr(attr: Triple<Int, String, String>?) {
        attr?.let {
            val itemEntity = uiState.value.itemEntity
            when (it.third) {
                "department" -> {
                    itemEntity.departmentId = it.first
                    itemEntity.department = it.second
                }
                "size" -> {
                    itemEntity.sizeId = it.first
                    itemEntity.size = it.second
                }
                "color" -> {
                    itemEntity.color = Color(id = it.first, name = it.second)
                }
                "category" -> {
                    itemEntity.categoryId = it.first
                    itemEntity.category = it.second
                }
                "sub_category" -> {
                    itemEntity.subcategoryId = it.first
                    itemEntity.subcategory = it.second
                }
                "brand" -> {
                    itemEntity.brandId = it.first
                    itemEntity.brand = it.second
                }
            }
        }
    }

    fun addItem() {
        viewModelScope.launch(dispatcherProvider.io) {
            val itemEntity = uiState.value.itemEntity
            if (itemEntity.itemName.isNullOrEmpty()) {
                _uiState.update { it.copy(error = "Invalid item name") }
                return@launch
            }
            if (itemEntity.itemId.isEmpty()) {
                _uiState.update { it.copy(error = "Invalid item id") }
                return@launch
            }
            if (itemEntity.sellingPrice == null) {
                _uiState.update { it.copy(error = "Invalid selling price") }
                return@launch
            }
            if (itemEntity.costPrice == null) {
                _uiState.update { it.copy(error = "Invalid cost price") }
                return@launch
            }

            if (_uiState.value.imageFile != null) {
                //upload image
            }

        }
    }

    fun onValueChange() {
        viewModelScope.launch(dispatcherProvider.io) {
            val stateCopy = uiState.value.copy()
            _uiState.update { AddItemScreenUiState() }
            delay(200)
            _uiState.update { stateCopy }
        }
    }

}