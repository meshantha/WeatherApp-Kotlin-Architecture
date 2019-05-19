package com.example.weather.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.weather.configs.PrefKeys.PREF_NAME
import com.example.weather.data.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class DataModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideSharedPreference(): SharedPreferences =
        app.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository = WeatherRepository(weatherApi)
}