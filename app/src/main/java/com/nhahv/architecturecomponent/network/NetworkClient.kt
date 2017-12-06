package com.nhahv.architecturecomponent.network

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by nhahv on 12/6/17.
 */
class NetworkClient(private val context: Context) {

    private val CONNECTION_TIMEOUT = 60L
    private val END_POINT = ""

    fun getAPI(): NetworkAPI {
        val httpClientBuilder = OkHttpClient.Builder()
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        httpClientBuilder.cache(Cache(context.cacheDir, cacheSize))
        httpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        httpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        val retrofit = Retrofit.Builder()
                .baseUrl(END_POINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .client(httpClientBuilder.build())
                .build()

        return retrofit.create(NetworkAPI::class.java)
    }

    private fun createGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: NetworkClient? = null

        fun getInstance(context: Context) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: NetworkClient(context).also { INSTANCE = it }

                }
    }
}

interface NetworkAPI {

}