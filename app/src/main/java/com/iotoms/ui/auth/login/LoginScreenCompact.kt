package com.iotoms.ui.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.iotoms.ui.theme.MediumPadding
import com.iotoms.ui.theme.PrimaryTeal
import com.iotoms.ui.theme.PrimaryTealLight
import com.iotoms.ui.theme.SmallPadding
import com.iotoms.R
import com.iotoms.data.model.FormError

/**
 * Created by Fasil on 01/11/2025
 */
@Composable
fun LoginScreenCompact(
    formError: FormError,
    username: String,
    password: String,
    domain: String,
    registerId: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onDomainChange: (String) -> Unit,
    onRegisterIdChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    var isPwdToggled by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
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
        Column(modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(MediumPadding)) {
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
                ),
                errorText = if (formError.errors?.first == "username") formError.errors.second else null
            )
            Spacer(modifier = Modifier.Companion.height(SmallPadding))
            OutlinedTextBox(
                value = password,
                onValueChange = onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Password")
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
                errorText = if (formError.errors?.first == "password") formError.errors.second else null
            )
            Spacer(modifier = Modifier.Companion.height(SmallPadding))
            OutlinedTextBox(
                value = domain,
                onValueChange = onDomainChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Domain")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                errorText = if (formError.errors?.first == "domain") formError.errors.second else null
            )
            Spacer(modifier = Modifier.Companion.height(SmallPadding))
            OutlinedTextBox(
                value = registerId,
                onValueChange = onRegisterIdChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(text = "Register Id")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                errorText = if (formError.errors?.first == "regId") formError.errors.second else null
            )
            Spacer(modifier = Modifier.Companion.height(MediumPadding))
            FilledButton(
                text = "Login",
                onClick = onLoginClick
            )
            Spacer(modifier = Modifier.Companion.height(MediumPadding))
            OutlinedButton(
                text = "Create Account",
                onClick = onCreateAccountClick
            )
        }
    }
}