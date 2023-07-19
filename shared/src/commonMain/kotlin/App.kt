import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import presentation.screen.common.model.Home
import presentation.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        Surface(
            Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigator(Home)
        }
    }
}

expect fun getPlatformName(): String

expect class Platform() {
    val name: String
}