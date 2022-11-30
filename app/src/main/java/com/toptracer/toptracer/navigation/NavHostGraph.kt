package com.toptracer.toptracer.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.toptracer.login.presentation.LoginScreen
import com.toptracer.login.presentation.LoginViewModel
import com.toptracer.navigation.destinations.LoginDestination
import com.toptracer.navigation.destinations.NavigationDestination
import com.toptracer.navigation.destinations.WelcomeDestination
import com.toptracer.welcome.presentation.WelcomeScreen
import com.toptracer.welcome.presentation.WelcomeViewModel

private fun composableDestinations(): Map<NavigationDestination, @Composable () -> Unit> =
    mapOf(
        LoginDestination to {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                viewModel.loginState.value,
                viewModel::onButtonClicked,
                viewModel::onErrorDismissed
            )
        },
        WelcomeDestination to {
            val viewModel: WelcomeViewModel = hiltViewModel()
            WelcomeScreen(viewModel.welcomeState.value, viewModel::onLogoutClicked)
        }
    )

fun NavGraphBuilder.addComposableDestinations() {
    composableDestinations().forEach { entry ->
        val destination = entry.key
        composable(
            destination.route, destination.arguments, destination.deepLinks
        ) {
            if (destination.route.startsWith(WelcomeDestination.WELCOME_ROUTE)) {
                val viewModel: WelcomeViewModel = hiltViewModel()
                viewModel.welcomeState.value = viewModel.welcomeState.value
                    .copy(username = it.arguments?.getString(WelcomeDestination.Params.USERNAME))
                WelcomeScreen(viewModel.welcomeState.value, viewModel::onLogoutClicked)
            } else {
                entry.value()
            }
        }
    }
}

