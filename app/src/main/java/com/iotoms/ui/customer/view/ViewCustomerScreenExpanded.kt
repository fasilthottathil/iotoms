package com.iotoms.ui.customer.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iotoms.R
import com.iotoms.ui.components.OutlinedTextBox
import com.iotoms.ui.theme.ExtraSmallPadding
import com.iotoms.ui.theme.NeutralGray600

/**
 * Created by Fasil on 08/11/2025
 */
@Composable
fun ViewCustomerScreenExpanded(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .padding(ExtraSmallPadding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(NeutralGray600)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier.size(90.dp)
                )
            }
            Spacer(Modifier.width(ExtraSmallPadding))
            Column {
                OutlinedTextBox(
                    value = "Customer Name",
                    onValueChange = { },
                    readOnly = true
                )
                Spacer(Modifier.height(ExtraSmallPadding))
                OutlinedTextBox(
                    value = "Customer Number",
                    onValueChange = { },
                    readOnly = true
                )
            }
        }
        OutlinedTextBox(
            value = "Address",
            onValueChange = { },
            readOnly = true
        )
        Spacer(Modifier.height(ExtraSmallPadding))
        Row {
            OutlinedTextBox(
                value = "Email",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(ExtraSmallPadding))
            OutlinedTextBox(
                value = "Phone",
                onValueChange = { },
                readOnly = true,
                modifier = Modifier.weight(1f)
            )
        }
    }
}