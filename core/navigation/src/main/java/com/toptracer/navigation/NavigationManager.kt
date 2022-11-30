package com.toptracer.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class NavigationManager @Inject constructor() : INavigationManager {

    private val _navigationEvents = MutableSharedFlow<NavigationEvent?>()

    override val navigationEvents = _navigationEvents

    override suspend fun navigateUp() {
        _navigationEvents.emit(NavigationEvent.NavigateUp)
    }

    override suspend fun navigateTo(route: String, builder: NavOptionsBuilder.() -> Unit) {
        _navigationEvents.emit(NavigationEvent.NavigateTo(route, builder))
    }
}
