package com.mayank.offline_simulator


object OfflineDataRegistry {
    private val data = mutableMapOf<String, Any>()

    fun <T : Any> register(key: String, value: T) {
        data[key] = value
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String): T? = data[key] as? T
}