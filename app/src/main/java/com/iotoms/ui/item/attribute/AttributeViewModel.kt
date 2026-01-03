package com.iotoms.ui.item.attribute

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iotoms.di.DispatcherProvider
import com.iotoms.domain.usecase.attribute.GetBrandsFromLocalUseCase
import com.iotoms.domain.usecase.attribute.GetCategoriesFromLocalUseCase
import com.iotoms.domain.usecase.attribute.GetColorsFromLocalUseCase
import com.iotoms.domain.usecase.attribute.GetDepartmentsFromLocalUseCase
import com.iotoms.domain.usecase.attribute.GetSizesFromLocalUseCase
import com.iotoms.domain.usecase.attribute.GetSubCategoriesFromLocalUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by Fasil on 03/01/2026
 */
class AttributeViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val getDepartmentsFromLocalUseCase: GetDepartmentsFromLocalUseCase,
    private val getSizesFromLocalUseCase: GetSizesFromLocalUseCase,
    private val getColorsFromLocalUseCase: GetColorsFromLocalUseCase,
    private val getCategoriesFromLocalUseCase: GetCategoriesFromLocalUseCase,
    private val getSubCategoriesFromLocalUseCase: GetSubCategoriesFromLocalUseCase,
    private val getBrandsFromLocalUseCase: GetBrandsFromLocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AttributeScreenUiState>(AttributeScreenUiState.Idle)
    val uiState: StateFlow<AttributeScreenUiState> = _uiState

    fun getAttributes(attrType: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            when (attrType) {
                "department" -> {
                    getDepartmentsFromLocalUseCase().collectLatest {
                        _uiState.update { state ->
                            AttributeScreenUiState.Data(it.map { entity ->
                                Pair(
                                    entity.id,
                                    entity.name.orEmpty()
                                )
                            })
                        }
                    }
                }
                "size" -> {
                    getSizesFromLocalUseCase().collectLatest {
                        _uiState.update { state ->
                            AttributeScreenUiState.Data(it.map { entity ->
                                Pair(
                                    entity.id,
                                    entity.name.orEmpty()
                                )
                            })
                        }
                    }
                }
                "color" -> {
                    getColorsFromLocalUseCase().collectLatest {
                        _uiState.update { state ->
                            AttributeScreenUiState.Data(it.map { entity ->
                                Pair(
                                    entity.id,
                                    entity.name.orEmpty()
                                )
                            })
                        }
                    }
                }
                "category" -> {
                    getCategoriesFromLocalUseCase().collectLatest {
                        _uiState.update { state ->
                            AttributeScreenUiState.Data(it.map { entity ->
                                Pair(
                                    entity.id,
                                    entity.name.orEmpty()
                                )
                            })
                        }
                    }
                }
                "sub_category" -> {
                    getSubCategoriesFromLocalUseCase().collectLatest {
                        _uiState.update { state ->
                            AttributeScreenUiState.Data(it.map { entity ->
                                Pair(
                                    entity.id,
                                    entity.name.orEmpty()
                                )
                            })
                        }
                    }
                }
                "brand" -> {
                    getBrandsFromLocalUseCase().collectLatest {
                        _uiState.update { state ->
                            AttributeScreenUiState.Data(it.map { entity ->
                                Pair(
                                    entity.id,
                                    entity.name.orEmpty()
                                )
                            })
                        }
                    }
                }
            }
        }
    }
}