package com.mayank.offline_simulator

interface OfflineAware {
    fun getOfflinePolicy(): OfflinePolicy

    fun getDummyDataKey(): String? = null

    fun onDummyDataReceived(data: Any) {
        // Optional to override
    }
}