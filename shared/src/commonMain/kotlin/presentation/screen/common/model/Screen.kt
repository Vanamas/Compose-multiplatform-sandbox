package presentation.screen.common.model

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import presentation.screen.ComponentScreen
import presentation.screen.gallery.ImageChooserScreen
import presentation.screen.gallery.ImageChooserViewModel
import presentation.screen.home.HomeScreen
import presentation.screen.home.HomeViewModel
import presentation.screen.post.PostDetailScreen
import presentation.screen.post.PostViewModel
import presentation.screen.post.PostsScreen
import presentation.screen.post.PostsViewModel


object Home : Screen {
    @Composable
    override fun Content() {
        val appViewModel: HomeViewModel = koinInject()
        val screenModel = rememberScreenModel { appViewModel }
        HomeScreen(screenModel)
    }
}

/**
 * Represents the "Posts" screen in the application.
 */
object Posts : Screen {

    @Composable
    override fun Content() {
        val postViewModel: PostsViewModel = koinInject()
        val screenModel = rememberScreenModel { postViewModel }
        PostsScreen(screenModel)
    }
}

/**
 * Represents the "Post Detail" screen in the application.
 *
 * This screen expects a route argument "id" representing the post's ID.
 */
data class PostDetail(val id: Long) : Screen {
    @Composable
    override fun Content() {
        val postViewModel: PostViewModel = koinInject { parametersOf(id) }
        val screenModel = rememberScreenModel { postViewModel }
        PostDetailScreen(screenModel)
    }
}

object ImageChooser : Screen {
    @Composable
    override fun Content() {
        val imageChooserViewModel: ImageChooserViewModel = koinInject()
        val screenModel = rememberScreenModel { imageChooserViewModel }
        ImageChooserScreen(screenModel)
    }
}

object Components : Screen {
    @Composable
    override fun Content() {
        ComponentScreen()
    }
}