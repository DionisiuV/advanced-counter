package com.valentin.advancedcounter.model.service.networkService


import android.util.Log
import com.valentin.advancedcounter.model.data.MockApiDataResponse
import com.valentin.advancedcounter.model.data.RequestModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceProvider : NetworkService{


    override fun getOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor { message -> Log.d("DEBUG_TAG_OK_HTTP", message) }.setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(interceptor)
            .build()
    }

    override fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("http://api.server.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getMockClient(): ApiService {
        return getRetrofit(getOkHttpClient(MockInterceptor())).create(ApiService::class.java)
    }

    fun addClicksAmount(clicksAmount: Int, onResult: (String?) -> Unit) {
        val clicksAmountRequestModel = RequestModel(clicksAmount)
        getMockClient().addClicksAmount(clicksAmountRequestModel).enqueue(object: Callback<MockApiDataResponse> {
            override fun onFailure(call: Call<MockApiDataResponse>, t: Throwable) {
                onResult(null)
                Log.d("ANDROID_DEBUG_TAG", t.toString())
            }
            override fun onResponse(call: Call<MockApiDataResponse>, response: Response<MockApiDataResponse>) {
                Log.d("ANDROID_DEBUG_TAG", "addClicksAmount() onResponse(): ${response.body().toString()}")
                onResult(response.body().toString())
            }
        })
    }

}