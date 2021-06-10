package com.aqube.mvi.data.remote.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {

    fun create(isDebug: Boolean, baseUrl: String, apiKey: String): NewsService {
        val retrofit = createRetrofit(isDebug, baseUrl, apiKey)
        return retrofit.create(NewsService::class.java)
    }

    private fun createRetrofit(isDebug: Boolean, baseUrl: String, apiKey: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient(isDebug, apiKey))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(isDebug: Boolean, apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createHeaderInterceptor(apiKey))
            .addInterceptor(createLoggingInterceptor(isDebug))
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private fun createHeaderInterceptor(apiKey: String): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter(API_KEY_CONST, apiKey)
                .build()

            val newRequest = request.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }

    private const val OK_HTTP_TIMEOUT = 60L
    private const val API_KEY_CONST = "apiKey"
}
