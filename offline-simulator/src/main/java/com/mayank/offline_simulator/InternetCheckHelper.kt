package com.mayank.offline_simulator

import android.app.Activity
import android.content.Context
import androidx.lifecycle.*


object InternetCheckHelper {
    fun attachLifecycleObserver(owner: LifecycleOwner) {
        if (owner is OfflineAware && owner is Context) {
            owner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onCreate(owner: LifecycleOwner) {
                    val context = owner as Context
                    val offlineAware = owner as OfflineAware

                    NetworkMonitor(context).isInternetAccessible { isOnline ->
                        if (!isOnline) {
                            when (offlineAware.getOfflinePolicy()) {
                                OfflinePolicy.SHOW_OVERLAY -> NoInternetOverlay(context as Activity).show()
                                OfflinePolicy.USE_DUMMY_DATA -> {
                                    val key = offlineAware.getDummyDataKey()
                                    val data = key?.let { OfflineDataRegistry.get<Any>(it) }
                                    data?.let { offlineAware.onDummyDataReceived(it) }
                                }
                            }
                        }
                    }
                }
            })
        }
    }
}
