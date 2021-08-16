package com.maciel.murillo.network.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import com.maciel.murillo.util.extensions.isNotNull
import com.maciel.murillo.util.extensions.safe
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(
    context: Context
) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (isOnline().not()) {
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (SDK_INT >= M) {
            checkNetworkWithActiveNetwork(connectivityManager)
        } else {
            checkNetworkWith(connectivityManager)
        }
    }

    private fun checkNetworkWithActiveNetwork(manager: ConnectivityManager): Boolean {
        val capabilities = if (SDK_INT >= M) {
            manager.getNetworkCapabilities(manager.activeNetwork)
        } else null

        return when {
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR).safe() -> return true
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI).safe() -> return true
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET).safe() -> return true
            else -> false
        }
    }

    private fun checkNetworkWith(manager: ConnectivityManager): Boolean {
        val networkInfo = manager.activeNetworkInfo
        return networkInfo.isNotNull() && networkInfo?.isConnected.safe()
    }
}