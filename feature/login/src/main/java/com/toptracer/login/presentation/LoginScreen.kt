package com.toptracer.login.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.toptracer.login.R


@Composable
fun LoginScreen(
    state: LoginState,
    onLoginClicked: (String, String) -> Unit,
    onErrorDismissed: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        TextField(
            label = { Text(text = stringResource(R.string.input_username)) },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = stringResource(R.string.input_password)) },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
        )
        {
            Text(
                color = Color.Gray,
                text = stringResource(R.string.forgot_password)
            )
            Text(
                modifier = Modifier
                    .clickable {
                        onLoginClicked(username.value.text, password.value.text)
                    }
                    .padding(start = 100.dp),
                color = Color.Blue,
                text = stringResource(R.string.button_login)
            )
        }
        DisplayAlertDialog(state, onErrorDismissed)
    }
}

@Composable
fun DisplayAlertDialog(
    state: LoginState,
    onErrorDismissed: () -> Unit
) {
    if (state.error) {
        AlertDialog(
            onDismissRequest = {
                onErrorDismissed()
            },
            title = {
                Text(text = "Oops!")
            },
            text = {
                Text(
                    when (state.errorMessage) {
                        Error.None -> ""
                        Error.EmptyField -> stringResource(R.string.empty_usename_password)
                        Error.WrongPassword -> stringResource(R.string.wrong_password)
                    }
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { onErrorDismissed() }
                    ) {
                        Text("Ok")
                    }
                }
            }
        )
    }
}


