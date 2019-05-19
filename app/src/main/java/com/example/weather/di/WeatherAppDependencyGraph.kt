package com.example.weather.di

import com.example.weather.ui.home.HomeComponent
import com.example.weather.ui.home.HomeModule

interface WeatherAppDependencyGraph {

    fun plus(homeModule: HomeModule): HomeComponent
}