package com.example.weather.domain

import com.example.weather.bases.SchedulerProvider
import com.example.weather.bases.domain.ObservableUseCase
import com.example.weather.data.api.models.GetWeatherByCountryResponse
import com.example.weather.data.repositories.WeatherRepository
import io.reactivex.Observable
import io.reactivex.ObservableSource
import timber.log.Timber

class GetWeatherByCountryUseCase(
    private val weatherRepository: WeatherRepository,
    schedulerProvider: SchedulerProvider
) : ObservableUseCase<GetWeatherByCountryUseCase.Result, GetWeatherByCountryUseCase.Params>(
    schedulerProvider.io(),
    schedulerProvider.ui()
) {
    override fun buildUseCaseObservable(params: Params?): Observable<Result> {
        Timber.d("buildUseCaseObservable, $params")
        return weatherRepository.getWeatherByCountry()
            .map<Result> {
                Timber.d("buildUseCaseObservable, map")
                return@map Result.Success(it)
            }
            .onErrorReturn {
                it.printStackTrace()
                Result.Error
            }
    }

    sealed class Result {
        object Error : Result()
        class Success(val res: GetWeatherByCountryResponse) : Result()
    }

    class Params
}