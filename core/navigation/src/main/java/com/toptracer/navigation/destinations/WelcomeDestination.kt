package com.toptracer.navigation.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object WelcomeDestination : NavigationDestination {

    object Params {
        const val USERNAME = "username"
    }

    const val WELCOME_ROUTE = "welcome"
    private const val WELCOME_ROUTE_WITH_KEY =
        "$WELCOME_ROUTE/{${Params.USERNAME}}"

    override val route = WELCOME_ROUTE_WITH_KEY
    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(Params.USERNAME) { type = NavType.StringType }
        )

    fun createWelcomeRoute(username: String) = WELCOME_ROUTE + "/${username}"

}
