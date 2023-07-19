import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIDevice

actual class Platform actual constructor() {

    actual val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}
actual fun getPlatformName(): String = "iOS"

fun MainViewController() = ComposeUIViewController { App() }