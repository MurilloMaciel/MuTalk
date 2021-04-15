package com.example.network.network

import com.example.network.connectivity.ConnectivityInterceptor

abstract class BaseRetrofitServiceCompanion<T> {

    inline fun <reified T> create(
        baseUrl: String,
        connectivityInterceptor: ConnectivityInterceptor? = null
    ): T = createNetworkClient(
        baseUrl = baseUrl,
        connectivityInterceptor = connectivityInterceptor
    ).create(T::class.java)
}