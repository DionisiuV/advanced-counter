package com.valentin.advancedcounter.model.service.networkService


import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceProvider : NetworkService{
    override fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    override fun getRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl("http://fakeURL.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}