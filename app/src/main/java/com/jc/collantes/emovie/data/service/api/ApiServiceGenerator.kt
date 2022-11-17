package com.jc.collantes.emovie.data.service.api

import com.google.gson.Gson
import com.jc.collantes.emovie.data.model.api.ApiResponse
import com.jc.collantes.emovie.data.model.api.GenericErrorApiResponse
import com.jc.collantes.emovie.data.model.api.SuccessApiResponse
import com.jc.collantes.emovie.utils.CONTENT_TYPE
import com.jc.collantes.emovie.utils.CONTENT_TYPE_VALUE
import com.jc.collantes.emovie.utils.TIME_OUT_CONNECT
import com.jc.collantes.emovie.utils.TIME_OUT_READ
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

class ApiServiceGenerator(
    private val baseUrl: String,
    private val apiKey: String?=null,
    private val languages: String?= null
) {

    private lateinit var retrofit: Retrofit
    private val headerMap = mutableMapOf<String, String>()
    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()
        headerMap[CONTENT_TYPE] = CONTENT_TYPE_VALUE

        val newBuilder = original.url.newBuilder()
            .addQueryParameter("api_key",apiKey)
        if (languages!= null) newBuilder.addQueryParameter("language",languages)
        val url = newBuilder
            .build()

        val headers = getHeaders().addAll(original.headers).build()

        val request = original.newBuilder()
            .url(url)
            .headers(headers)
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    init {
        setupRetrofit()
    }

    private fun setupRetrofit() {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.connectTimeout(TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getHeaders(): Headers.Builder {
        val header: Headers.Builder = Headers.Builder()
        headerMap.forEach { (key, value) ->
            header.add(key, value)
        }
        return header
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    suspend fun <Data> processCallWithError(responseCall: suspend () -> Response<Data>): ApiResponse<Data?> {

        val response: Response<Data>?
        return try {
            response = responseCall.invoke()
            if (response.isSuccessful) {
                SuccessApiResponse(response.body())
            } else {
                return GenericErrorApiResponse(response.code(), response.message())
            }
        } catch (ex: Exception) {
            GenericErrorApiResponse(-2, ex.message)
        }
    }


}