package com.nhahv.architecturecomponent.ui.main

import android.arch.lifecycle.MutableLiveData
import com.nhahv.architecturecomponent.ui.BaseViewModel
import com.nhahv.architecturecomponent.util.Navigator

/**
 * Created by hoang.van.nha on 12/6/2017.
 */
class MainViewModel(private val navigator: Navigator) : BaseViewModel() {
    val textShow: MutableLiveData<String> = MutableLiveData()
}