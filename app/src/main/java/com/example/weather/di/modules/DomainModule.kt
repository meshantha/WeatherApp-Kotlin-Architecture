package com.example.weather.di.modules

import com.example.weather.bases.SchedulerProvider
import com.example.weather.data.repositories.WeatherRepository
import com.example.weather.domain.GetWeatherByCountryUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class DomainModule {

    @Provides
    @Singleton
    fun provideGetWeatherByContryUseCase(
        weatherRepository: WeatherRepository,
        schedulerProvider: SchedulerProvider
    ): GetWeatherByCountryUseCase =
        GetWeatherByCountryUseCase(weatherRepository, schedulerProvider)
}