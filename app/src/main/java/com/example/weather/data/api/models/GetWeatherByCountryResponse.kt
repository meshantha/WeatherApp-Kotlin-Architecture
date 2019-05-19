package com.example.weather.data.api.models

import com.example.weather.data.models.*
import com.google.gson.annotations.SerializedName

data class GetWeatherByCountryResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    @SerializedName("main")
    val weatherSummary: WeatherSummary,
    val name: String,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)