package com.toptracer.navigation.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

interface NavigationDestination {
    val route: String
    val arguments: List<NamedNavArgument>
        get() = emptyList()

    val deepLinks: List<NavDeepLink>
        get() = emptyList()
}
