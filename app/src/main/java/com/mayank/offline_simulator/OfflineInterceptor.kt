package com.mayank.offline_simulator

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class OfflineInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (OfflineStateManager.isOffline) {
            throw IOException("Simulated Offline Mode")
        }
        return chain.proceed(chain.request())
    }
}