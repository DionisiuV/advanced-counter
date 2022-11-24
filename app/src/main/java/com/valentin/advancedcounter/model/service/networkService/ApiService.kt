package com.valentin.advancedcounter.model.service.networkService

import com.valentin.advancedcounter.model.data.MockApiDataResponse
import com.valentin.advancedcounter.model.data.RequestModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("clicks-amount")
    fun addClicksAmount(@Body requestModel: RequestModel): Single<MockApiDataResponse>
}