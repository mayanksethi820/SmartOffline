package com.mayank.offline_simulator

interface OfflineAware {
    fun getOfflinePolicy(): OfflinePolicy

    // Optional
    fun getOfflineDataProvider(): (suspend () -> Any)? = null

    // Called only if policy is USE_DUMMY_DATA
    fun onDummyDataReceived(data: Any)
}