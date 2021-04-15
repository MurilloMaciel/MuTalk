package com.example.network.network

import com.example.network.connectivity.ConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 30L
private const val READ_TIMEOUT = 30L

private fun createRetrofitClient(
    baseUrl: String,
    okHttp: OkHttpClient,
    gsonConverter: GsonConverterFactory,
) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttp)
        .addConverterFactory(gsonConverter)
        .build()

private fun getGsonConverter() = GsonConverterFactory.create()

private fun getHttpClient(
    connectivityInterceptor: ConnectivityInterceptor? = null
): OkHttpClient {

    val httpClient = OkHttpClient.Builder()

    connectivityInterceptor?.run {
        httpClient.addInterceptor(this)
    }

    return httpClient
        .addInterceptor(getLoggingInterceptor())
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .build()
}

private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
//    level = if (BuildConfig.DEBUG) {
//        HttpLoggingInterceptor.Level.BODY
//    } else {
//        HttpLoggingInterceptor.Level.NONE
//    }
    level = HttpLoggingInterceptor.Level.NONE
}

fun createNetworkClient(
    baseUrl: String,
    connectivityInterceptor: ConnectivityInterceptor? = null
): Retrofit = createRetrofitClient(
    baseUrl = baseUrl,
    okHttp = getHttpClient(connectivityInterceptor),
    gsonConverter = getGsonConverter(),
)
