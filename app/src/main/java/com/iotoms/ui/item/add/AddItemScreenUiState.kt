package com.iotoms.ui.item.add

import com.iotoms.data.local.entity.ItemEntity
import java.io.File

/**
 * Created by Fasil on 03/01/2026
 */
data class AddItemScreenUiState(
    var isLoading: Boolean = false,
    var itemEntity: ItemEntity = ItemEntity(itemId = ""),
    var imageFile: File? = null,
    var error: String? = null,
    var isItemAdded: Boolean = false
)