package com.iotoms.ui.auth.emp.pin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.iotoms.ui.components.FilledButton
import com.iotoms.ui.components.OutlinedButton
import com.iotoms.ui.components.OutlinedTextBox
import com.iotoms.ui.theme.LargePadding
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.PrimaryTeal
import com.iotoms.ui.theme.PrimaryTealLight
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.R

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun EmployeePinLoginScreenCompact(
    pin: String,
    onPinChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onPasswordLoginClick: () -> Unit
) {
    var isPwdToggled by rememberSaveable { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            PrimaryTeal,
                            PrimaryTealLight
                        )
                    )
                )
                .weight(0.4f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "OnePos Logo"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(MediumPadding)
        ) {
            OutlinedTextBox(
                value = pin,
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Pin")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (isPwdToggled) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Icon(
                        imageVector = if (isPwdToggled) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = SmallPadding)
                            .clickable { isPwdToggled = !isPwdToggled }
                    )
                },
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
                            modifier = Modifier.weight(1f),
                            text = number,
                            onClick = {
                                onPinChange(pin + number)
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
                    modifier = Modifier.weight(1f),
                    text = "",
                    onClick = {
                        if (pin.isNotEmpty()) {
                            onPinChange(pin.dropLast(1))
                        }
                    }, leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = null
                        )
                    }
                )
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    text = "0",
                    onClick = {
                        onPinChange(pin + "0")
                    }
                )
                FilledButton(
                    modifier = Modifier.weight(1f),
                    text = "",
                    onClick = onLoginClick,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = null
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.Companion.height(LargePadding))
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Text("Login With Password")
                },
                onClick = onPasswordLoginClick
            )

        }
    }
}