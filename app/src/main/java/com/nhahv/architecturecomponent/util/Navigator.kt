package com.nhahv.architecturecomponent.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nhahv.architecturecomponent.ui.BaseActivity

/**
 * Created by hoang.van.nha on 12/6/2017.
 */
class Navigator(val context: Context) {

    val TAG = "TAG"

    fun log(message: String) {
        Log.d(TAG, message)
    }

    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T : BaseActivity> switchUI() {
        context.startActivity(Intent(context, T::class.java))
    }

    inline fun <reified T : BaseActivity> switchUI(bundle: Bundle) {
        context.startActivity(Intent(context, T::class.java).putExtras(bundle))
    }

}