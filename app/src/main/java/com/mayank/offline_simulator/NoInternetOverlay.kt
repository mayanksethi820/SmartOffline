package com.mayank.offline_simulator

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT

class NoInternetOverlay(private val activity: Activity) {
    private var overlayView: View? = null

    fun show() {
        if (overlayView == null) {
            overlayView = LayoutInflater.from(activity).inflate(R.layout.view_no_internet, null)
            activity.addContentView(
                overlayView,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            )
        }
    }

    fun hide() {
        (overlayView?.parent as? ViewGroup)?.removeView(overlayView)
        overlayView = null
    }
}