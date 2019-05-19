package com.example.weather.bases

import androidx.multidex.MultiDexApplication
import com.example.weather.di.WeatherAppDependencyGraph

interface BaseApplication {
    fun component(): WeatherAppDependencyGraph
}