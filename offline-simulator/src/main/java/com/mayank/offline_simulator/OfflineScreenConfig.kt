package com.mayank.offline_simulator

import android.graphics.Color
import android.view.View

data class OfflineScreenConfig(
    val imageResId: Int = R.drawable.ic_no_internet,
    val imageTintColor: Int? = null,
    val message: String = "No Internet Connection",
    val buttonText: String = "Retry",
    val buttonColor: Int = Color.RED,
    val onRetry: (() -> Unit)? = null,
    val customView: View? = null
)