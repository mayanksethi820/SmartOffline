package com.mayank.offline_simulator

object OfflineStateManager {
    var isOffline = false

    fun enableOfflineMode() { isOffline = true }
    fun disableOfflineMode() { isOffline = false }
    fun toggle() { isOffline = !isOffline }
}