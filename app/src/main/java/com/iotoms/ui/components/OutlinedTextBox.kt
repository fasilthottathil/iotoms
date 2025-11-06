package com.iotoms.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.iotoms.ui.theme.PrimaryTeal
import com.iotoms.ui.theme.TextBoxMinHeight

/**
 * Created by Fasil on 01/11/2025
 */
@Composable
fun OutlinedTextBox(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorText: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth().heightIn(min = TextBoxMinHeight),
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = ShapeDefaults.Medium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryTeal,
            unfocusedBorderColor = PrimaryTeal.copy(alpha = 0.5f),
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
        ),
        readOnly = readOnly,
        isError = isError,
        supportingText = {
            if (isError && errorText != null) {
                Text(
                    text = errorText,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}