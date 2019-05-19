package com.example.weather.data.repositories

import com.example.weather.data.api.models.GetWeatherByCountryResponse
import io.reactivex.Observable

class WeatherRepository(private val weatherApi: WeatherApi) {
    fun getWeatherByCountry(country: String = "Hong Kong"): Observable<GetWeatherByCountryResponse> =
        weatherApi.getWeatherByCountry(country)
}