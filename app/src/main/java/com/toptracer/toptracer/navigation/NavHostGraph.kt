package com.toptracer.toptracer.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toptracer.navigation.destinations.LoginDestination
import com.toptracer.navigation.destinations.NavigationDestination

private fun composableDestinations(): Map<NavigationDestination, @Composable () -> Unit> =
    mapOf(
        LoginDestination to {
            LoginScreen()
        }
    )

@Composable
fun LoginScreen() {
    Text(text = "Login Screen")
}

fun NavGraphBuilder.addComposableDestinations() {
    composableDestinations().forEach { entry ->
        val destination = entry.key
        composable(
            destination.route, destination.arguments, destination.deepLinks
        ) {
            entry.value()
        }
    }
}

