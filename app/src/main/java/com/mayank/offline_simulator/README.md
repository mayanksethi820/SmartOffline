# Offline UI Simulator 🔌📴

A lightweight Android library to simulate offline behavior inside your app for development and testing purposes.

Supports:
- Retrofit/OkHttp offline simulation
- Jetpack Compose offline UI switching
- Debug overlay to toggle offline mode at runtime

---

## 🚀 Features

✅ Simulate offline Retrofit API failures  
✅ Automatically swap to fallback UI in Compose  
✅ Debug switch overlay in dev builds  
✅ Minimal and extensible setup

---

## 📦 Installation

Add the library using [JitPack](https://jitpack.io) (after publishing):

### `settings.gradle.kts`
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}


build.gradle.kts

dependencies {
    implementation("com.github.yourusername:offline-ui-simulator:1.0.0")
}

🛠️ Usage
✅ 1. Enable Debug Overlay (optional)
Add this in your Application class:

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(DebugOverlay())
    }
}

✅ 2. Use OfflineWrapper in Jetpack Compose
kotlin
Copy
Edit
@Composable
fun ProductScreen() {
    OfflineWrapper(
        content = { ProductList() },
        fallback = { OfflinePlaceholder() }
    )
}
✅ 3. Use OfflineInterceptor in Retrofit
kotlin
Copy
Edit
val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(OfflineInterceptor())
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .client(okHttpClient)
    .build()
✨ Advanced
Toggle offline programmatically
kotlin
Copy
Edit
OfflineStateManager.enableOfflineMode()
OfflineStateManager.disableOfflineMode()
OfflineStateManager.toggle()



🧩 How Developers Can Customize:

val overlayView = LayoutInflater.from(context).inflate(R.layout.view_no_internet, null)
val imageView = overlayView.findViewById<ImageView>(R.id.noInternetImage)
imageView.setImageResource(R.drawable.custom_offline_image)

val textView = overlayView.findViewById<TextView>(R.id.noInternetMessage)
textView.text = "Oops! No network available."

val retryButton = overlayView.findViewById<Button>(R.id.btnRetryInternet)
retryButton.setOnClickListener {
    if (NetworkMonitor(context).isConnected()) {
        overlay.hide()
    }
}
