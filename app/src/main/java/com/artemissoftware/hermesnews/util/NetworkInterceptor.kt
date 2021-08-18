package com.artemissoftware.hermesnews.util

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import android.net.NetworkCapabilities

import android.os.Build

import android.net.ConnectivityManager


class NetworkInterceptor(var context: Context?): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isConnected()) {
            throw NoConnectivityException()
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }


    private fun isConnected(): Boolean {

        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw)
            actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH))
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo
            nwInfo != null && nwInfo.isConnected
        }
    }
}