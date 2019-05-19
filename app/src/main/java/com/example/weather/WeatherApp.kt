package com.example.weather

import androidx.multidex.MultiDexApplication
import com.example.weather.bases.BaseApplication
import com.example.weather.di.*
import com.example.weather.di.components.DaggerWeatherAppComponent
import com.example.weather.di.components.WeatherAppComponent
import com.example.weather.di.modules.DataModule
import com.facebook.stetho.Stetho
import timber.log.Timber

class WeatherApp : MultiDexApplication(), BaseApplication {

    lateinit var component: WeatherAppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerWeatherAppComponent.builder()
            .dataModule(DataModule(this))
            .build()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun component(): WeatherAppDependencyGraph = component
}