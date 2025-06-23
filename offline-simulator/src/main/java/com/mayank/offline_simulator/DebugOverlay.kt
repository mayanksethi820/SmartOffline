package com.mayank.offline_simulator

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.widget.Switch
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.app.AlertDialog

class DebugOverlay : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        val switch = Switch(activity).apply {
            text = "Offline Mode"
            isChecked = OfflineStateManager.isOffline
            setOnCheckedChangeListener { _, isChecked ->
                OfflineStateManager.isOffline = isChecked
            }
        }
        activity.addContentView(switch, ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
    }

    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {
        showNoInternetDialogIfNeeded(activity)
    }
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}

    fun showNoInternetDialogIfNeeded(context: Context) {
        NetworkMonitor(context).isInternetAccessible { connected ->
            if (!connected) {
                AlertDialog.Builder(context)
                    .setTitle("No Internet")
                    .setMessage("You're offline. Some features may not work.")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }
}