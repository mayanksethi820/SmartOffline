package com.mayank.offline_simulator

import android.app.Activity
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class NoInternetOverlay(
    private val activity: Activity,
    private val config: OfflineScreenConfig = OfflineScreenConfig()
) {
    private var overlayView: View? = null

    fun show() {
        if (overlayView != null) return

        overlayView = if (config.customView != null) {
            config.customView
        } else {
            val inflater = LayoutInflater.from(activity)
            val view = inflater.inflate(R.layout.view_no_internet, null)

            val image = view.findViewById<ImageView>(R.id.noInternetImage)
            val message = view.findViewById<TextView>(R.id.noInternetMessage)
            val retry = view.findViewById<Button>(R.id.btnRetryInternet)

            image.setImageResource(config.imageResId)
            config.imageTintColor?.let {
                image.setColorFilter(it, PorterDuff.Mode.SRC_IN)
            }
            message.text = config.message
            retry.text = config.buttonText
            retry.setBackgroundColor(config.buttonColor)
            retry.setOnClickListener {
                config.onRetry?.invoke()
            }

            view
        }

        activity.addContentView(
            overlayView,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
    }

    fun hide() {
        (overlayView?.parent as? ViewGroup)?.removeView(overlayView)
        overlayView = null
    }
}
