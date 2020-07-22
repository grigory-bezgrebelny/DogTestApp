package com.bezgrebelnygregory.testapp.core.api

import com.bezgrebelnygregory.testapp.BuildConfig
import com.google.gson.GsonBuilder
import org.koin.core.module.Module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun Module.apiModule() {
    single { getJsonConverter() }
    single { getAppService(jsonConverter = get()) }
    single { ApiHelper() }
}

private fun getAppService(jsonConverter: GsonConverterFactory): Api =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(jsonConverter)
        .build()
        .create(Api::class.java)

private fun getJsonConverter(): GsonConverterFactory =
    GsonConverterFactory.create(GsonBuilder().create())