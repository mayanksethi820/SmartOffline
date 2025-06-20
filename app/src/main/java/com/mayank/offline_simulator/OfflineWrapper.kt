package com.mayank.offline_simulator

import androidx.compose.runtime.Composable

@Composable
fun OfflineWrapper(
    content: @Composable () -> Unit,
    fallback: @Composable () -> Unit
) {
    if (OfflineStateManager.isOffline) {
        fallback()
    } else {
        content()
    }
}