package com.valentin.advancedcounter.model.service.networkService

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.valentin.advancedcounter.model.data.RequestModel
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer


class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //i need to intercept data so i can send back response with clicks amount attached
        val requestBodyBuffer = Buffer()
        chain.request().body?.writeTo(requestBodyBuffer)
        val requestModel = parseRequest(requestBodyBuffer)

        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.contains("clicks-amount") -> printResponseFromMockApi(requestModel.clicksAmount)
            uri.endsWith("/") -> getResponseFromMockApi
            else -> throw Exception("not valid route")
        }

        return Response.Builder()
            .request(chain.request())
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(getResponseFromMockApi)
            .body(responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }
}

const val getResponseFromMockApi = """
        {"message": "Mock api reached"}
        """

private fun printResponseFromMockApi(clicksAmount: Int): String {
    return """
        {"message": "The element clicked $clicksAmount times was Registered"}
        """
}

private fun parseRequest(requestBody: Buffer): RequestModel {
    val jsonObjectRequestBody: JsonObject = JsonParser.parseString(requestBody.readUtf8()).asJsonObject
    return Gson().fromJson(jsonObjectRequestBody, RequestModel::class.java)
}

