package com.iotoms.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation3.runtime.NavKey
import com.iotoms.ui.components.ErrorOutlinedBox
import com.iotoms.ui.components.FilledButton
import com.iotoms.ui.components.OutlinedButton
import com.iotoms.ui.components.OutlinedTextBox
import com.iotoms.ui.theme.MediumBarHeight
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.utils.extensions.formatAmount
import kotlinx.serialization.Serializable

/**
 * Created by Fasil on 07/11/2025
 */
@Composable
fun GeneralItemCalculatorScreen(uiState: State<CartUiState>, onClickAdd: (String) -> Unit) {
    var amount by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(uiState.value) {
        error = if (uiState.value is CartUiState.Error) {
            (uiState.value as CartUiState.Error).message
        } else {
            ""
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MediumPadding)
    ) {
        if (error.isNotEmpty()) {
            ErrorOutlinedBox(error = error, onDismiss = { error = "" })
            Spacer(modifier = Modifier.Companion.height(MediumPadding))
        }
        OutlinedTextBox(
            value = amount,
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Amount")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Decimal
            ),
            readOnly = true
        )
        Spacer(modifier = Modifier.Companion.height(MediumPadding))
        repeat(3) { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(SmallPadding),
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(state = rememberScrollState())
            ) {
                repeat(3) { col ->
                    val number = (row * 3 + col + 1).toString()
                    OutlinedButton(
                        modifier = Modifier
                            .height(MediumBarHeight)
                            .weight(1f),
                        text = number,
                        onClick = {
                            amount = (amount + number).formatAmount()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.Companion.height(SmallPadding))
        }

        // Last Row: Clear, 0, Delete
        Row(
            horizontalArrangement = Arrangement.spacedBy(SmallPadding),
            modifier = Modifier.fillMaxWidth()
        ) {
            FilledButton(
                modifier = Modifier
                    .height(MediumBarHeight)
                    .weight(1f),
                text = "",
                onClick = {
                    if (amount.isNotEmpty()) {
                        val newRaw = amount.dropLast(1)
                        amount = if (newRaw.isEmpty()) "" else newRaw.formatAmount()
                    }
                }, leadingIcon = {
                    Text("C")
                }
            )
            OutlinedButton(
                modifier = Modifier
                    .height(MediumBarHeight)
                    .weight(1f),
                text = "0",
                onClick = {
                    amount = (amount + "0").formatAmount()
                }
            )
            FilledButton(
                modifier = Modifier
                    .height(MediumBarHeight)
                    .weight(1f),
                text = "",
                onClick = {
                    if (amount.isNotEmpty()) {
                        onClickAdd(amount)
                        amount = ""
                    }
                },
                leadingIcon = {
                    Text("ADD")
                }
            )
        }
    }
}