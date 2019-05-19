package com.example.weather.bases.domain

interface SynchronousUseCase<out Results, in Params> {
    fun execute(params: Params? = null): Results
}