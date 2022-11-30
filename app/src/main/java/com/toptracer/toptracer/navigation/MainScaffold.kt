package com.toptracer.toptracer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.toptracer.navigation.INavigationManager
import com.toptracer.navigation.NavigationEvent
import com.toptracer.navigation.destinations.LoginDestination

@Composable
fun MainScaffold(
    navigationManager: INavigationManager
) {
    val navController = rememberAnimatedNavController()
    NavigationEvent(
        navController = navController,
        navigationManager = navigationManager
    )
    AnimatedNavHost(
        navController = navController,
        startDestination = LoginDestination.route,
        builder = {
            addComposableDestinations()
        }
    )
}

@Composable
private fun NavigationEvent(
    navController: NavHostController,
    navigationManager: INavigationManager
) {
    LaunchedEffect(navController) {
        navigationManager.navigationEvents.collect {
            when (val event = it) {
                is NavigationEvent.NavigateUp -> navController.navigateUp()
                is NavigationEvent.NavigateTo ->
                    navController.navigate(
                        event.route,
                        event.builder
                    )
                else -> {}
            }
        }
    }
}

