package com.example.weather.di.view_model

import androidx.lifecycle.ViewModel
import dagger.MapKey

import kotlin.reflect.KClass
import kotlin.annotation.Retention

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)