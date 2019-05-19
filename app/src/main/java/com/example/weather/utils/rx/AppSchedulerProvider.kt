package com.example.weather.utils.rx

import com.example.weather.bases.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : SchedulerProvider {
    override fun io() = Schedulers.io()

    override fun ui() = AndroidSchedulers.mainThread()

    override fun newThread() = Schedulers.newThread()

    override fun computation() = Schedulers.computation()

}