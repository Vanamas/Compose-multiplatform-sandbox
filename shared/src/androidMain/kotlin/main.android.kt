import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

actual class Platform {
    actual val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"

    init {
        Napier.base(DebugAntilog())
    }
}
actual fun getPlatformName(): String = "Android"

@Composable fun MainView() = App()

@Composable
actual fun Test() {
    Text("Android")
}
