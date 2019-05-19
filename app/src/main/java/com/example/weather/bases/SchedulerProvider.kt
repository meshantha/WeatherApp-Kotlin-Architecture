package com.example.weather.bases

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun newThread(): Scheduler
    fun computation(): Scheduler
}