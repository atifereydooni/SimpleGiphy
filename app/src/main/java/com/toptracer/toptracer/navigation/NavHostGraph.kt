package com.toptracer.toptracer.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.toptracer.login.presentation.LoginScreen
import com.toptracer.login.presentation.LoginViewModel
import com.toptracer.navigation.destinations.LoginDestination
import com.toptracer.navigation.destinations.NavigationDestination

private fun composableDestinations(): Map<NavigationDestination, @Composable () -> Unit> =
    mapOf(
        LoginDestination to {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel.loginState.value,
                viewModel::onButtonClicked,
                viewModel::onErrorDismissed
            )
        }
    )

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

