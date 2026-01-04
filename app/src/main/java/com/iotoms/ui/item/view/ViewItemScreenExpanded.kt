package com.iotoms.ui.item.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.iotoms.R
import com.iotoms.data.local.entity.ItemEntity
import com.iotoms.ui.components.OutlinedTextBox
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.utils.extensions.getOrZero

/**
 * Created by Fasil on 29/12/2025
 */
@Composable
fun ViewItemScreenExpanded(modifier: Modifier, itemEntity: ItemEntity?) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SmallPadding)
            .verticalScroll(state = rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = itemEntity?.imageGallery?.imageUrl,
                    placeholder = painterResource(R.drawable.product_placeholder),
                    error = painterResource(R.drawable.product_placeholder)
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(ShapeDefaults.Small),
                contentScale = ContentScale.Crop
            )
            Text(
                text = itemEntity?.itemName.orEmpty(),
                style = MaterialTheme.typography.titleMedium
            )
            if (itemEntity?.description.isNullOrEmpty().not()) {
                Spacer(Modifier.height(SmallPadding))
                Text(
                    text = itemEntity.description.orEmpty(),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        Spacer(Modifier.height(MediumPadding))
        Row {
            Column(Modifier.weight(1f)) {
                Text(
                    "Item Id",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    value = itemEntity?.itemId.orEmpty(),
                    onValueChange = { },
                    readOnly = true
                )
            }
            Spacer(Modifier.width(ExtraSmallPadding))
            Column(Modifier.weight(1f)) {
                if (itemEntity?.upc.isNullOrEmpty().not()) {
                    Spacer(Modifier.height(SmallPadding))
                    Text(
                        "UPC",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(start = ExtraSmallPadding)
                    )
                    OutlinedTextBox(
                        value = itemEntity.upc.orEmpty(),
                        onValueChange = { },
                        readOnly = true
                    )
                } else {
                    Column(Modifier.weight(1f)) {
                        Text(
                            "Selling Price",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(start = ExtraSmallPadding)
                        )
                        OutlinedTextBox(
                            value = itemEntity?.sellingPrice.getOrZero().toString(),
                            onValueChange = { },
                            readOnly = true
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(SmallPadding))
        Row {
            if (itemEntity?.upc.isNullOrEmpty().not()) {
                Column(Modifier.weight(1f)) {
                    Text(
                        "Selling Price",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(start = ExtraSmallPadding)
                    )
                    OutlinedTextBox(
                        value = itemEntity.sellingPrice.getOrZero().toString(),
                        onValueChange = { },
                        readOnly = true
                    )
                }
                Spacer(Modifier.width(ExtraSmallPadding))
            }
            Column(Modifier.weight(1f)) {
                Text(
                    "Cost Price",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    value = itemEntity?.costPrice.getOrZero().toString(),
                    onValueChange = { },
                    readOnly = true
                )
            }
        }
        Spacer(Modifier.height(SmallPadding))
        Row {
            Column(Modifier.weight(1f)) {
                Text(
                    "Department",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    value = itemEntity?.department.orEmpty().ifEmpty { "Not selected" },
                    onValueChange = { },
                    readOnly = true
                )
            }
            Spacer(Modifier.width(ExtraSmallPadding))
            Column(Modifier.weight(1f)) {
                Text(
                    "Color",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    value = itemEntity?.color?.name.orEmpty().ifEmpty { "Not selected" },
                    onValueChange = { },
                    readOnly = true
                )
            }
        }
        Spacer(Modifier.height(SmallPadding))
        Row {
            Column(Modifier.weight(1f)) {
                Text(
                    "Size",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    value = itemEntity?.size.orEmpty().ifEmpty { "Not selected" },
                    onValueChange = { },
                    readOnly = true
                )
            }
            Spacer(Modifier.width(ExtraSmallPadding))
            Column(Modifier.weight(1f)) {
                Text(
                    "Color",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    value = itemEntity?.brand.orEmpty().ifEmpty { "Not selected" },
                    onValueChange = { },
                    readOnly = true
                )
            }
        }
        Spacer(Modifier.height(SmallPadding))
        Row {
            Column(Modifier.weight(1f)) {
                Text(
                    "Category",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    value = itemEntity?.category.orEmpty().ifEmpty { "Not selected" },
                    onValueChange = { },
                    readOnly = true
                )
            }
            Spacer(Modifier.width(ExtraSmallPadding))
            Column(Modifier.weight(1f)) {
                Text(
                    "Sub Category",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    value = itemEntity?.subcategory.orEmpty().ifEmpty { "Not selected" },
                    onValueChange = { },
                    readOnly = true
                )
            }
        }
    }
}