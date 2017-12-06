package com.nhahv.architecturecomponent.data.source.local

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by hoang.van.nha on 12/6/2017.
 */
class SharePrefs private constructor(context: Context) {
    private val sharePrefs: SharedPreferences = context.getSharedPreferences("SharePrefs", Context.MODE_PRIVATE)


    companion object {
        @Volatile private var INSTANCE: SharePrefs? = null
        fun getInstance(context: Context) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: SharePrefs(context).also { INSTANCE = it }
                }
    }

    fun <T> put(key: String, value: T) {
        when (value) {
            is String -> sharePrefs.edit().putString(key, value).apply()
            is Int -> sharePrefs.edit().putInt(key, value).apply()
            is Float -> sharePrefs.edit().putFloat(key, value).apply()
            is Boolean -> sharePrefs.edit().putBoolean(key, value).apply()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String, type: Class<T>): T? =
            when (type) {
                String::class.java -> sharePrefs.getString(key, null) as T
                Boolean::class.java -> sharePrefs.getBoolean(key, false) as T
                Int::class.java -> sharePrefs.getInt(key, 0) as T
                Float::class.java -> sharePrefs.getFloat(key, 0f) as T
                else -> {
                    throw IllegalArgumentException("Unknown ViewModel class: ${type.name}")
                }
            }
}
