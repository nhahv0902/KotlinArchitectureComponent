package com.nhahv.architecturecomponent.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

/**
 * Created by hoang.van.nha on 12/6/2017.
 * base activity for all activity extends
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    val TAG = "TAG"

    inline fun <reified T : ViewModel> obtainViewModel() =
            ViewModelProviders.of(this, ViewModelFactory.getInstance(this)).get(T::class.java)

    inline fun <reified T : BaseActivity> switchUI() {
        startActivity(Intent(this, T::class.java))
    }


    inline fun <reified T : BaseActivity> switchUI(bundle: Bundle) {
        startActivity(Intent(this, T::class.java).putExtras(bundle))
    }

    fun log(message: String) {
        Log.d(TAG, message)
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}