package com.example.weather.di.components

import com.example.weather.di.WeatherAppDependencyGraph
import com.example.weather.di.modules.DomainModule
import com.example.weather.di.modules.ViewModelModule
import com.example.weather.di.modules.WeatherAppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [WeatherAppModule::class, DomainModule::class, ViewModelModule::class])
interface WeatherAppComponent : WeatherAppDependencyGraph