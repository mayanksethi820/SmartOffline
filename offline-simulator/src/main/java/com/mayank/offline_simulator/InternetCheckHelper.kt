package com.mayank.offline_simulator

import android.app.Activity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


object InternetCheckHelper {
    fun attachLifecycleObserver(owner: LifecycleOwner) {
        if (owner is OfflineAware && owner is Activity) { // 🔁 Note: Activity instead of Context
            owner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onCreate(owner: LifecycleOwner) {
                    val offlineOwner = owner as OfflineAware
                    val activity = owner as Activity // ✅ Now it's an Activity

                    NetworkMonitor(activity).isInternetAccessible { isOnline ->
                        if (!isOnline) {
                            when (offlineOwner.getOfflinePolicy()) {
                                OfflinePolicy.SHOW_OVERLAY -> NoInternetOverlay(activity).show()
                                OfflinePolicy.USE_DUMMY_DATA -> {
                                    val provider = offlineOwner.getOfflineDataProvider()
                                    if (provider != null) {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            val data = provider()
                                            withContext(Dispatchers.Main) {
                                                offlineOwner.onDummyDataReceived(data)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            })
        }
    }
}



