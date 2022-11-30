package com.toptracer.navigation.di

import com.toptracer.navigation.INavigationManager
import com.toptracer.navigation.NavigationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Singleton
    @Binds
    abstract fun navigationManager(navigator: NavigationManager): INavigationManager
}
