package com.example.weather.bases.domain

import com.example.weather.utils.rx.EmptyObserver
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<Results, in Params>(
    executionThread: Scheduler,
    postExecutionThread: Scheduler
) : BaseReactiveUseCase(executionThread, postExecutionThread) {

    abstract fun buildUseCaseObservable(params: Params? = null): Observable<Results>

    fun execute(observer: DisposableObserver<Results> = EmptyObserver(), params: Params? = null) {
        val observable = buildUseCaseObservableWithSchedulers(params)
        addDisposable(observable.subscribeWith(observer))
    }

    private fun buildUseCaseObservableWithSchedulers(params: Params?): Observable<Results> {
        return buildUseCaseObservable(params)
            .subscribeOn(executionThread)
            .observeOn(postExecutionThread)
    }
}