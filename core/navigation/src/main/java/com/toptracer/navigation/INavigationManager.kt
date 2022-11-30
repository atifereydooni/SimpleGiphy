package com.toptracer.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.SharedFlow

interface INavigationManager {

    val navigationEvents: SharedFlow<NavigationEvent?>
    suspend fun navigateTo(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }
    )

    suspend fun navigateUp()
}
