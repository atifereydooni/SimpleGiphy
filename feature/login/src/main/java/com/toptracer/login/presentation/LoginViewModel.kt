package com.toptracer.login.presentation

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toptracer.navigation.INavigationManager
import com.toptracer.navigation.destinations.LoginDestination
import com.toptracer.navigation.destinations.WelcomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val navigationManager: INavigationManager,
) : ViewModel() {

    val loginState: MutableState<LoginState> = mutableStateOf(LoginState(errorMessage = Error.None))

    fun onButtonClicked(username: String, password: String) {
        login(username, password)
    }

    private fun login(username: String, password: String) {
        if (username.isValid() && password.isValid()) {
            if (password == "password") {
                navigateToWelcomeScreen(username)
            } else {
                loginState.value = loginState.value.copy(
                    errorMessage = Error.WrongPassword,
                    error = true
                )
            }
        } else {
            loginState.value = loginState.value.copy(
                errorMessage = Error.EmptyField,
                error = true
            )
        }
    }

    fun onErrorDismissed() {
        loginState.value = loginState.value.copy(
            error = false
        )
    }

    private fun navigateToWelcomeScreen(username: String) {
        viewModelScope.launch {
            navigationManager.navigateTo(WelcomeDestination.createWelcomeRoute(username)) {
                popUpTo(LoginDestination.route) {
                    inclusive = true
                }
            }

        }
    }

}

fun CharSequence.isValid(): Boolean {
    return !TextUtils.isEmpty(this)
}

