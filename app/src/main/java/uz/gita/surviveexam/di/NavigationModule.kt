package uz.gita.surviveexam.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.surviveexam.navigation.AppNavigator
import uz.gita.surviveexam.navigation.NavigationDispatcher
import uz.gita.surviveexam.navigation.NavigationHandler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @[Binds Singleton]
    fun bindAppNavigator(impl:NavigationDispatcher):AppNavigator

    @[Binds Singleton]
    fun bindNavigationHandler(impl: NavigationDispatcher):NavigationHandler
}