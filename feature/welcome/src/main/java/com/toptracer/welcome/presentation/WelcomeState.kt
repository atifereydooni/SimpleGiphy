package com.toptracer.welcome.presentation

import com.toptracer.welcome.domain.entity.GiphyEntity


data class WelcomeState(
    var username: String? = "",
    var giphyEntity: GiphyEntity? = null,
    val errorMessage: Error? = Error.None,
    val loading: Boolean? = false,
)

enum class Error {
    None, ResponseError
}
