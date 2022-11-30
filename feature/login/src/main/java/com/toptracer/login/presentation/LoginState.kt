package com.toptracer.login.presentation


data class LoginState(
    var error: Boolean = false,
    val errorMessage: Error
)

enum class Error {
    None, EmptyField, WrongPassword
}
