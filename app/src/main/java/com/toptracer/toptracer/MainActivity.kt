package com.toptracer.toptracer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.toptracer.navigation.INavigationManager
import com.toptracer.theme.TopTracerTheme
import com.toptracer.toptracer.navigation.MainScaffold
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: INavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopTracerTheme {
                MainScaffold(
                    navigationManager = navigationManager
                )
            }
        }
    }
}
