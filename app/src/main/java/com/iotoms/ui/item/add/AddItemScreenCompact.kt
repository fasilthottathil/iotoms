package com.iotoms.ui.item.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.iotoms.ui.components.DropDownBox
import com.iotoms.ui.components.ImageUploadSection
import com.iotoms.ui.components.OutlinedTextBox
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.SmallPadding

/**
 * Created by Fasil on 03/01/2026
 */
@Composable
fun AddItemScreenCompact() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(SmallPadding)
        .verticalScroll(rememberScrollState())
    ) {
        ImageUploadSection(
            onAddPhoto = {

            },
            onTakePhoto = {

            }
        )
        Spacer(Modifier.height(MediumPadding))
        Text(
            "Product Name*",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = ExtraSmallPadding)
        )
        OutlinedTextBox(
            placeholder = {
                Text("Enter item name")
            },
            value = "",
            onValueChange = { }
        )
        Spacer(Modifier.height(SmallPadding))
        Text(
            "Item Id*",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = ExtraSmallPadding)
        )
        OutlinedTextBox(
            placeholder = {
                Text("Enter item id")
            },
            value = "",
            onValueChange = { }
        )
        Spacer(Modifier.height(SmallPadding))
        Text(
            "UPC",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = ExtraSmallPadding)
        )
        OutlinedTextBox(
            placeholder = {
                Text("Enter UPC")
            },
            value = "",
            onValueChange = { }
        )

        Spacer(Modifier.height(MediumPadding))

        Row {
            Column(Modifier.weight(1f)) {
                Text(
                    "Selling Price",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    placeholder = {
                        Text("Enter selling price")
                    },
                    value = "",
                    onValueChange = { }
                )
            }
            Spacer(Modifier.width(SmallPadding))
            Column(Modifier.weight(1f)) {
                Text(
                    "Cost Price",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start = ExtraSmallPadding)
                )
                OutlinedTextBox(
                    placeholder = {
                        Text("Enter cost price")
                    },
                    value = "",
                    onValueChange = { }
                )
            }
        }

        Spacer(Modifier.height(SmallPadding))
        Text(
            "Category",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start = ExtraSmallPadding)
        )
        DropDownBox(
            modifier = Modifier.fillMaxWidth(),
            text = "Select category",
            onClick = { }
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
            onClick = { }
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
            onClick = { }
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
            onClick = { }
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
            onClick = { }
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
            onClick = { }
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
            onClick = { }
        )
    }
}