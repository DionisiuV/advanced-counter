package com.valentin.advancedcounter.model.service.networkService


import android.util.Log
import com.valentin.advancedcounter.model.data.RequestModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceProvider : NetworkService {


    private fun getOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor { message -> Log.d("DEBUG_TAG_OK_HTTP", message) }.setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(interceptor)
            .build()
    }

     private fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("http://valentin-mock-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun getMockClient(): ApiService {
        return getRetrofit(getOkHttpClient(MockInterceptor())).create(ApiService::class.java)
    }

    override fun sendClicksAmount(clicksAmount: Int) {
        getMockClient()
            .addClicksAmount(RequestModel(clicksAmount))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { e ->
                    Log.e("NETWORK_SERVICE_ERROR", "addClicksAmount: $e")
                          },
                onSuccess = { response ->
                    Log.d("NETWORK_SERVICE_SUCCESS", response.message)
                }
            )
    }

}