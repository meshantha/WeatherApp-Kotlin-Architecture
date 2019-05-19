package com.example.weather.di.modules

import com.example.weather.configs.ApiConfig.OPEN_WEATHER_API_BASE_URL
import com.example.weather.configs.ApiConfig.OPEN_WEATHER_API_KEY
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .addInterceptor {
            val newRequest = it.request().newBuilder()
                .url(
                    it.request().url().newBuilder().addQueryParameter("APPID", OPEN_WEATHER_API_KEY).build()
                )
                .build()

            it.proceed(newRequest)
        }
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(OPEN_WEATHER_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}