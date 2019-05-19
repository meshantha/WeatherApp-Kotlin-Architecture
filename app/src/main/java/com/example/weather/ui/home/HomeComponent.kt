package com.example.weather.ui.home

import com.example.weather.di.WeatherAppDependencyGraph
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(homeFragment: HomeFragment)

    object Initializer {
        fun init(weatherAppDependencyGraph: WeatherAppDependencyGraph) = weatherAppDependencyGraph.plus(HomeModule())
    }
}