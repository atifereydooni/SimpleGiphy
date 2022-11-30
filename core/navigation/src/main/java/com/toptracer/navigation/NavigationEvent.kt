package com.toptracer.navigation

import androidx.navigation.NavOptionsBuilder

sealed class NavigationEvent {

    object NavigateUp : NavigationEvent()
    class NavigateTo(
        val route: String,
        val builder: NavOptionsBuilder.() -> Unit = {}
    ) : NavigationEvent()
}
