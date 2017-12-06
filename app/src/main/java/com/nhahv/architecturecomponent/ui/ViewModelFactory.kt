package com.nhahv.architecturecomponent.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.nhahv.architecturecomponent.ui.main.MainViewModel
import com.nhahv.architecturecomponent.util.Navigator

/**
 * Created by hoang.van.nha on 12/6/2017.
 */
class ViewModelFactory private constructor(
        private val context: Context
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(MainViewModel::class.java) ->
                        MainViewModel(Navigator(context))
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: ViewModelFactory(context).also { INSTANCE = it }
                }
    }
}