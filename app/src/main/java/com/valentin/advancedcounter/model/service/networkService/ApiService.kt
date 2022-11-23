package com.valentin.advancedcounter.model.service.networkService

import com.valentin.advancedcounter.model.data.MockApiDataResponse
import com.valentin.advancedcounter.model.data.RequestModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("mock-data")
    fun provideData(): Call<MockApiDataResponse>

    @POST("clicks-amount")
    fun addClicksAmount(@Body requestModel: RequestModel): Call<MockApiDataResponse>

}