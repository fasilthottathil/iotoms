package com.iotoms.ui.item.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iotoms.ui.components.DropDownBox
import com.iotoms.ui.components.ErrorOutlinedBox
import com.iotoms.ui.components.ImageUploadSection
import com.iotoms.ui.components.OutlinedTextBox
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.utils.extensions.formatAmount
import com.iotoms.utils.extensions.getOrZero
import java.io.File

/**
 * Created by Fasil on 03/01/2026
 */
@Composable
fun AddItemScreenExpanded(
    uiState: State<AddItemScreenUiState>,
    onClickAttr: (String) -> Unit,
    onAddPhoto: () -> Unit,
    onTakePhoto: () -> Unit,
    fileUri: MutableState<File?>
) {
    val itemEntity = uiState.value.itemEntity
    var productName by remember { mutableStateOf(itemEntity.itemName) }
    var itemId by remember { mutableStateOf(itemEntity.itemId) }
    var productId by remember { mutableStateOf(itemEntity.productId) }
    var upc by remember { mutableStateOf(itemEntity.upc) }
    var sellingPrice by remember { mutableStateOf(itemEntity.sellingPrice) }
    var costPrice by remember { mutableStateOf(itemEntity.costPrice) }
    var description by remember { mutableStateOf(itemEntity.description) }
    var error by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(uiState.value) {
        error = uiState.value.error.orEmpty()
    }
    Column {
        if (error.isNotEmpty()) {
            Box(Modifier.padding(ExtraSmallPadding)){
                ErrorOutlinedBox(
                    error = error
                ) {
                    uiState.value.error = ""
                    error = ""
                }
            }
            Spacer(Modifier.height(MediumPadding))
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(MediumPadding)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .verticalScroll(rememberScrollState())
            ) {
                ImageUploadSection(
                    image = fileUri.value ?: itemEntity.imageGallery?.imageUrl,
                    onAddPhoto = onAddPhoto,
                    onTakePhoto = onTakePhoto
                )

                Spacer(Modifier.height(MediumPadding))

                Text(
                    "Selling Price",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    placeholder = { Text("Enter selling price") },
                    value = sellingPrice.getOrZero().toString().formatAmount(),
                    onValueChange = {
                        itemEntity.sellingPrice = it.toDoubleOrNull()
                        sellingPrice = itemEntity.sellingPrice
                        error = ""
                    }
                )

                Spacer(Modifier.height(SmallPadding))

                Text(
                    "Cost Price",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    placeholder = { Text("Enter cost price") },
                    value = costPrice.getOrZero().toString().formatAmount(),
                    onValueChange = {
                        itemEntity.costPrice = it.toDoubleOrNull()
                        costPrice = itemEntity.costPrice
                        error = ""
                    }
                )
            }

            Spacer(Modifier.width(MediumPadding))

            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    "Product Name*",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    placeholder = { Text("Enter item name") },
                    value = productName.orEmpty(),
                    onValueChange = {
                        itemEntity.itemName = it
                        productName = it
                        error = ""
                    }
                )

                Spacer(Modifier.height(SmallPadding))

                Row {
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Item Id*",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(start = ExtraSmallPadding)
                        )
                        OutlinedTextBox(
                            placeholder = { Text("Enter item id") },
                            value = itemId,
                            onValueChange = {
                                itemEntity.itemId = it
                                itemId = it
                                error = ""
                            }
                        )
                    }

                    Spacer(Modifier.width(SmallPadding))
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Product Id*",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(start = ExtraSmallPadding)
                        )
                        OutlinedTextBox(
                            placeholder = { Text("Enter product id") },
                            value = productId.orEmpty(),
                            onValueChange = {
                                itemEntity.productId = it
                                productId = it
                                error = ""
                            }
                        )
                    }
                    Spacer(Modifier.width(SmallPadding))

                    Column(Modifier.weight(1f)) {
                        Text(
                            "UPC",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(start = ExtraSmallPadding)
                        )
                        OutlinedTextBox(
                            placeholder = { Text("Enter UPC") },
                            value = upc.orEmpty(),
                            onValueChange = {
                                itemEntity.upc = it
                                upc = it
                                error = ""
                            }
                        )
                    }
                }
                Spacer(Modifier.height(SmallPadding))
                Text(
                    "Description",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    modifier = Modifier.heightIn(min = 150.dp),
                    placeholder = {
                        Text("Enter description")
                    },
                    value = description.orEmpty(),
                    onValueChange = {
                        itemEntity.description = it
                        description = it
                        error = ""
                    }
                )
                Spacer(Modifier.height(MediumPadding))
                Text(
                    "Category",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                DropDownBox(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Select category",
                    onClick = {
                        onClickAttr("category")
                        error = ""
                    }
                )

                Spacer(Modifier.height(MediumPadding))

                Text(
                    "Sub Category",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                DropDownBox(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Select sub category",
                    onClick = {
                        onClickAttr("sub_category")
                        error = ""
                    }
                )

                Spacer(Modifier.height(MediumPadding))

                Text(
                    "Brand",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                DropDownBox(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Select brand",
                    onClick = {
                        onClickAttr("brand")
                        error = ""
                    }
                )

                Spacer(Modifier.height(MediumPadding))

                Text(
                    "Department",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                DropDownBox(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Select department",
                    onClick = {
                        onClickAttr("department")
                        error = ""
                    }
                )

                Spacer(Modifier.height(MediumPadding))

                Text(
                    "Color",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                DropDownBox(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Select color",
                    onClick = {
                        onClickAttr("color")
                        error = ""
                    }
                )

                Spacer(Modifier.height(MediumPadding))

                Text(
                    "Size",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                DropDownBox(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Select size",
                    onClick = {
                        onClickAttr("size")
                        error = ""
                    }
                )

                Spacer(Modifier.height(MediumPadding))

                Text(
                    "Style",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                DropDownBox(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Select style",
                    onClick = {
                        onClickAttr("style")
                        error = ""
                    }
                )
            }
        }
    }
}