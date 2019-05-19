package com.example.weather.ui.home

import androidx.lifecycle.*
import com.example.weather.bases.SchedulerProvider
import com.example.weather.bases.ui.BaseViewData
import com.example.weather.data.api.models.GetWeatherByCountryResponse
import com.example.weather.data.repositories.WeatherRepository
import com.example.weather.domain.GetWeatherByCountryUseCase
import com.example.weather.ui.home.adapter.HomeWeatherItems
import com.example.weather.ui.home.adapter.SummaryViewData
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getWeatherByCountryUseCase: GetWeatherByCountryUseCase
) :
    ViewModel(), LifecycleObserver {

    val errorLiveData: MutableLiveData<String> = MutableLiveData()

    val viewDataLiveData: MutableLiveData<List<BaseViewData>> = MutableLiveData()

    private val useCaseObserver by lazy {
        object : DisposableObserver<GetWeatherByCountryUseCase.Result>() {
            override fun onStart() {
                super.onStart()
                Timber.d("onStart")
            }

            override fun onComplete() {

            }

            override fun onNext(res: GetWeatherByCountryUseCase.Result) {
                when (res) {
                    is GetWeatherByCountryUseCase.Result.Error -> {
                        Timber.d("Error")
                        errorLiveData.postValue("Please try again")
                    }
                    is GetWeatherByCountryUseCase.Result.Success -> {
                        Timber.d("Success")
                        errorLiveData.postValue(null)

                        val weatherSummary = res.res.weatherSummary
                        viewDataLiveData.postValue(
                            listOf(
                                SummaryViewData(
                                    rawData = weatherSummary,
                                    currentTemperature = "${weatherSummary.temp.toInt()}°",
                                    minMaxTemperature = "${weatherSummary.temp_max.toInt()}° / ${weatherSummary.temp_min.toInt()}°",
                                    weatherStatus = res.res.weather.firstOrNull()?.description ?: "",
                                    itemViewType = HomeWeatherItems.ITEM_SUMMARY
                                )
                            )
                        )
                    }
                }
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchData() {
        Timber.d("fetchData")
        getWeatherByCountryUseCase.execute(
            observer = useCaseObserver, params = GetWeatherByCountryUseCase.Params()
        )
    }

    override fun onCleared() {
        Timber.d("onCleared")
        getWeatherByCountryUseCase.dispose()
        super.onCleared()
    }
}