# 📴 SmartOffline

**SmartOffline** is a lightweight Android library that allows developers to simulate offline mode by:

- Detecting actual internet availability (not just network connectivity)
- Showing a customizable "No Internet" overlay
- Injecting dummy data for seamless offline user experience
- Simulating offline mode during development with a debug switch

---

## ✨ Features

- ✅ Detects true internet access using `generate_204` endpoint  
- ✅ Shows overlay when offline (fully customizable)  
- ✅ Supports both XML and Jetpack Compose apps  
- ✅ Optional dummy data registry for offline fallback  
- ✅ Retrofit support via `OfflineInterceptor`  
- ✅ Debug toggle switch for development-time simulation  

---

## 📦 Installation

1. Add this to `settings.gradle.kts`:
   ```kotlin
   dependencyResolutionManagement {
       repositories {
           maven { url = uri("https://jitpack.io") }
       }
   }
   ```

2. In `build.gradle.kts`:
   ```kotlin
   implementation("com.github.mayanksethi820:SmartOffline:1.0.0")
   ```

---

## 🚀 Usage

### 1. Implement `OfflineAware` (Optional)

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
        // Show fallback UI
    }
}
```

> 🔸 Implement `OfflineAware` only if you want to use dummy data on offline mode.

---

### 2. Register Dummy Data (Optional)

```kotlin
OfflineDataRegistry.register("products", listOf(Product("Sample", "₹99")))
```

---

### 3. Add Interceptor to Retrofit (Optional)

```kotlin
val client = OkHttpClient.Builder()
    .addInterceptor(OfflineInterceptor())
    .build()
```

---

### 4. Customize Overlay Layout

Edit `view_no_internet.xml`:

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

Simulate offline state manually in development with a UI switch:

```kotlin
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(DebugOverlay())
    }
}
```

---

## 💡 Coming Soon

- Jetpack Compose overlay  
- LiveData / StateFlow support  
- Dark mode theme support  
- Callbacks for internet status events  

---

## 📄 License

This project is licensed under the MIT License.  
See the [LICENSE](LICENSE) file for details.

---

## 🙌 Contributions

Pull requests, feature suggestions, and issues are welcome!  
Let’s build smarter offline-first experiences together. 🚀