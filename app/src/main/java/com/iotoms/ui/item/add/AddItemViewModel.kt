package com.iotoms.ui.item.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iotoms.data.mapper.toAddItemRequest
import com.iotoms.data.model.request.ImageGalleryRequest
import com.iotoms.data.model.response.Color
import com.iotoms.data.model.response.ImageGallery
import com.iotoms.di.DispatcherProvider
import com.iotoms.domain.usecase.file.FileUploadUseCase
import com.iotoms.domain.usecase.image_gallery.AddImageGalleryUseCase
import com.iotoms.domain.usecase.item.AddItemUseCase
import com.iotoms.utils.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

/**
 * Created by Fasil on 03/01/2026
 */
class AddItemViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val fileUploadUseCase: FileUploadUseCase,
    private val addImageGalleryUseCase: AddImageGalleryUseCase,
    private val addItemUseCase: AddItemUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddItemScreenUiState())
    val uiState: StateFlow<AddItemScreenUiState> = _uiState

    private var uploadedFile: File? = null
    private var imageGalleryId: Int? = null

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
            if (itemEntity.productId.isNullOrEmpty()) {
                _uiState.update { it.copy(error = "Invalid product id") }
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

            _uiState.update { it.copy(isLoading = true) }
            if (_uiState.value.imageFile != null && uploadedFile != _uiState.value.imageFile) {
                when (val result = fileUploadUseCase(_uiState.value.imageFile!!)) {
                    is Result.Success -> {
                        imageGalleryId = null
                        uploadedFile = _uiState.value.imageFile
                        itemEntity.imageGallery = ImageGallery(imageUrl = result.data.publicUrl)
                    }

                    is Result.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = result.error.message) }
                        return@launch
                    }
                }
            }

            if (imageGalleryId == null && itemEntity.imageGallery != null) {
                when (val result = addImageGalleryUseCase(ImageGalleryRequest(imageUrl = itemEntity.imageGallery?.imageUrl))) {
                    is Result.Success -> {
                        imageGalleryId = result.data.id
                        itemEntity.imageGallery?.id = result.data.id
                    }
                    is Result.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = result.error.message) }
                        return@launch
                    }
                }
            }

            when (val result = addItemUseCase(itemEntity.toAddItemRequest())) {
                is Result.Success -> {
                    _uiState.update { it.copy(isLoading = false, isItemAdded = true) }
                }
                is Result.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.error.message) }
                }
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