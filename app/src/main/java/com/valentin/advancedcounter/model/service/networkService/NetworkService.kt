package com.valentin.advancedcounter.model.service.networkService

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface NetworkService {

    fun getOkHttpClient(): OkHttpClient

    fun getRetrofit(gson: Gson): Retrofit
}