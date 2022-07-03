package com.example.taskexecuterapp.core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private val baseUrl = "https://dog.ceo/api/breeds/image/"

private val loggingInterceptor by lazy {
    HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }
}

private val okHttpClient by lazy {
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

val retrofit by lazy {
    Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .build()
}

inline fun <reified T : Any> buildApi(): T = retrofit.create(T::class.java)