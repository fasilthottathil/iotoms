package com.iotoms.ui.auth.reset

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.iotoms.ui.components.FilledButton
import com.iotoms.ui.components.OutlinedTextBox
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.PrimaryTeal
import com.iotoms.ui.theme.PrimaryTealLight
import com.iotoms.R

/**
 * Created by Fasil on 06/11/2025
 */
@Composable
fun ForgotPasswordScreenExpanded(
    username: String,
    onUsernameChange: (String) -> Unit,
    onResetClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            PrimaryTeal,
                            PrimaryTealLight
                        )
                    )
                )
                .weight(0.7f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.iotoms_logo),
                contentDescription = "OnePos Logo"
            )
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(MediumPadding),
            verticalArrangement = Arrangement.Center) {
            OutlinedTextBox(
                value = username,
                onValueChange = onUsernameChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Username")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(modifier = Modifier.Companion.height(MediumPadding))
            FilledButton(
                text = "Reset Password",
                onClick = onResetClick
            )
            Spacer(modifier = Modifier.Companion.height(MediumPadding))
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                content = { Text("Back to Login") },
                onClick = onLoginClick
            )
        }
    }
}