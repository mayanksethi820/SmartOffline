package com.mayank.offline_simulator

import android.app.Activity
import android.content.Context
import androidx.lifecycle.*


object InternetCheckHelper {
    private val offlineAwareMap = mutableMapOf<LifecycleOwner, OfflineAware>()

    fun register(owner: LifecycleOwner, offlineAware: OfflineAware) {
        offlineAwareMap[owner] = offlineAware
        attachLifecycleObserver(owner)
    }

    private fun attachLifecycleObserver(owner: LifecycleOwner) {
        val context = owner as? Context ?: return
        owner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                NetworkMonitor(context).isInternetAccessible { isOnline ->
                    if (!isOnline) {
                        val aware = offlineAwareMap[owner]
                        when (aware?.getOfflinePolicy()) {
                            OfflinePolicy.SHOW_OVERLAY -> NoInternetOverlay(context as Activity).show()
                            OfflinePolicy.USE_DUMMY_DATA -> {
                                val key = aware.getDummyDataKey()
                                val data = key?.let { OfflineDataRegistry.get<Any>(it) }
                                if (data != null) {
                                    aware.onDummyDataReceived(data)
                                } else {
                                    // fallback if dummy data not registered
                                    NoInternetOverlay(context as Activity).show()
                                }
                            }
                            else -> {
                                // fallback if OfflineAware not provided
                                NoInternetOverlay(context as Activity).show()
                            }
                        }
                    }
                }
            }

            override fun onDestroy(owner: LifecycleOwner) {
                offlineAwareMap.remove(owner)
            }
        })
    }
}

