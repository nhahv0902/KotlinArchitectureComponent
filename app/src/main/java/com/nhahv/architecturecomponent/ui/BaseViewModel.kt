package com.nhahv.architecturecomponent.ui

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscription

/**
 * Created by hoang.van.nha on 12/6/2017.
 */
open class BaseViewModel : ViewModel() {

    private val mDisposables: CompositeDisposable = CompositeDisposable()

    fun startSubscriber(subscription: Disposable) {
        mDisposables.add(subscription)
    }

    fun stopSubscriptions() {
        mDisposables.clear()
    }
}