package com.mayank.offline_simulator

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class SimulateOffline(
    val fallbackType: FallbackType = FallbackType.SKELETON
)

enum class FallbackType {
    SKELETON, PLACEHOLDER, ERROR_VIEW
}
