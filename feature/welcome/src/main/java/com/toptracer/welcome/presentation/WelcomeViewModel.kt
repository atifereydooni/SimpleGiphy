package com.toptracer.welcome.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toptracer.navigation.INavigationManager
import com.toptracer.navigation.destinations.LoginDestination
import com.toptracer.navigation.destinations.WelcomeDestination
import com.toptracer.welcome.domain.usecase.GetGiphyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel
@Inject constructor(
    private val navigationManager: INavigationManager,
    private val getGiphyUseCase: GetGiphyUseCase
) : ViewModel() {

    private val _welcomeState = mutableStateOf(WelcomeState())
    val welcomeState get() = _welcomeState

    init {
        getAllGiphy()
    }

    fun getAllGiphy() {
        _welcomeState.value = _welcomeState.value.copy(loading = true)
        viewModelScope.launch {
            getGiphyUseCase.getAllGiphy(API_KEY)
                .onSuccess {
                    _welcomeState.value = _welcomeState.value.copy(giphyEntity = it)
                }
                .onFailure {
                    _welcomeState.value =
                        _welcomeState.value.copy(errorMessage = Error.ResponseError)
                }
            _welcomeState.value = _welcomeState.value.copy(loading = false)
        }
    }

    fun onLogoutClicked() {
        viewModelScope.launch {
            navigationManager.navigateTo(LoginDestination.route) {
                popUpTo(WelcomeDestination.route) {
                    inclusive = true
                }
            }

        }
    }

}

const val API_KEY = "fnmGnCGH3S7IxvVKjIXP0NczEVuZmKG9"