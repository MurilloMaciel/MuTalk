package com.maciel.murillo.network.network

import com.maciel.murillo.network.connectivity.ConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 30L
private const val READ_TIMEOUT = 30L

fun createNetworkClient(
    baseUrl: String,
    connectivityInterceptor: ConnectivityInterceptor? = null
): Retrofit = createRetrofitClient(
    baseUrl = baseUrl,
    okHttp = getHttpClient(connectivityInterceptor),
    moshiConverter = MoshiConverterFactory.create(),
)

private fun createRetrofitClient(
    baseUrl: String,
    okHttp: OkHttpClient,
    moshiConverter: MoshiConverterFactory,
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttp)
    .addConverterFactory(moshiConverter)
    .build()

private fun getHttpClient(
    connectivityInterceptor: ConnectivityInterceptor? = null
): OkHttpClient = OkHttpClient.Builder()
    .apply { connectivityInterceptor?.let { addInterceptor(it) } }
    .addInterceptor(getLoggingInterceptor())
    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    .build()

private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
//    level = if (BuildConfig.DEBUG) {
//        HttpLoggingInterceptor.Level.BODY
//    } else {
//        HttpLoggingInterceptor.Level.NONE
//    }
    level = HttpLoggingInterceptor.Level.NONE
}
