package com.example.weather.di.modules

import com.example.weather.bases.SchedulerProvider
import com.example.weather.utils.rx.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WeatherAppModule {
    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}