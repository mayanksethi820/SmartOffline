package com.mayank.offline_simulator

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class NetworkMonitor(private val context: Context) {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isConnected(): Boolean {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    fun isInternetAccessible(callback: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = try {
                val url = URL("https://clients3.google.com/generate_204")
                val conn = url.openConnection() as HttpURLConnection
                conn.connectTimeout = 1500
                conn.connect()
                conn.responseCode == 204
            } catch (e: IOException) {
                false
            }
            withContext(Dispatchers.Main) { callback(result) }
        }
    }

    fun register(callback: (Boolean) -> Unit) {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) = callback(true)
            override fun onLost(network: Network) = callback(false)
        })
    }
}
