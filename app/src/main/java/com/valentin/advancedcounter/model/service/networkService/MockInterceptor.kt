package com.valentin.advancedcounter.model.service.networkService

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
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

        val buffer = Buffer()
        chain.request().body?.writeTo(buffer)
        val jsonRequestBody = buffer.readUtf8()
        val element: JsonElement = JsonParser.parseString(jsonRequestBody)
        val result = element.asJsonObject
        val objectRequest = Gson().fromJson(result, RequestModel::class.java)


        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.contains("data") -> getResponseFromMockApi
            uri.contains("amount") -> printResponseFromMockApi(objectRequest.clicksAmount)
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
        {"message": "Mocked api reached"}
        """

private fun printResponseFromMockApi(s: Int): String {
    return """
        {"message": "The element clicked $s times was Registered"}
        """
}

