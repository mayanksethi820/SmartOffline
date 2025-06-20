# offline-simulator
Android library to simulate offline mode by detecting real internet access, displaying a customizable "No Internet" overlay, and allowing developers to inject dummy data for a seamless offline experience.

# 📴 Offline UI Simulator

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/language-kotlin-orange.svg)](https://kotlinlang.org)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

Offline UI Simulator is a lightweight Android library that allows developers to:
- Detect **true internet availability**, not just connection.
- Show a **customizable "No Internet" overlay** on any screen.
- Provide **dummy fallback data** when the user is offline.
- Simulate offline mode during development via a debug switch.

---

## ✨ Features

✅ Detects actual internet access using `generate_204` test  
✅ Overlay view with retry option  
✅ Support for XML and Jetpack Compose apps  
✅ Dummy data registry for offline fallback  
✅ Retrofit support using `OfflineInterceptor`  
✅ Easy integration with minimal setup

---

## 📦 Installation

<details>
<summary><strong>Via local module (recommended for development)</strong></summary>

1. Clone or download this repository.
2. In your app's `settings.gradle.kts`:

```kotlin
include(":offline-ui-core")
project(":offline-ui-core").projectDir = File("../OfflineUiSimulator/offline-ui-core")
```

3. In `app/build.gradle.kts`:

```kotlin
dependencies {
    implementation(project(":offline-ui-core"))
}
```

</details>

---

## 🚀 Usage

### 1. Implement `OfflineAware` in your Activity/Fragment:

```kotlin
class ProductActivity : AppCompatActivity(), OfflineAware {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        InternetCheckHelper.attachLifecycleObserver(this)
    }

    override fun getOfflinePolicy() = OfflinePolicy.SHOW_OVERLAY

    override fun getDummyDataKey(): String = "products"

    override fun onDummyDataReceived(data: Any) {
        val productList = data as List<Product>
        // Show dummy data
    }
}
```

---

### 2. Register Dummy Data

```kotlin
OfflineDataRegistry.register("products", listOf(Product("Sample", "₹99")))
```

---

### 3. Add the `OfflineInterceptor` to Retrofit (Optional)

```kotlin
val client = OkHttpClient.Builder()
    .addInterceptor(OfflineInterceptor())
    .build()
```

---

### 4. Customize Overlay View

Edit `view_no_internet.xml` in your layout folder:

```xml
<ImageView
    android:id="@+id/imageNoInternet"
    android:src="@drawable/ic_offline"
    ... />

<TextView
    android:id="@+id/textMessage"
    android:text="No Internet Connection"
    ... />

<Button
    android:id="@+id/buttonRetry"
    android:text="Retry"
    ... />
```

---

## 🧪 Debug Mode

Enable the debug toggle switch in development:

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(DebugOverlay())
    }
}
```

---

## 📄 License

This project is licensed under the MIT License.  
See [LICENSE](LICENSE) for more details.

---

## 💡 Coming Soon

- LiveData or StateFlow support  
- Jetpack Compose overlay  
- Dark mode theme customization  
- No-internet event callbacks

---

## ✨ Contributions Welcome

Feel free to submit issues, pull requests, or feature suggestions!

