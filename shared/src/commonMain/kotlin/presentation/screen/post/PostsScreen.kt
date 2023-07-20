package presentation.screen.post

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.model.Post
import presentation.screen.common.BackButtonScaffold
import presentation.screen.common.UiStateScreen
import presentation.screen.common.model.PostDetail

/**
 * Screen with list of posts.
 *
 * @author Martin Vana
 */
@Composable
fun PostsScreen(postViewModel: PostsViewModel) {

    val state by postViewModel.state.collectAsState()

    val navigator = LocalNavigator.currentOrThrow

    BackButtonScaffold("Příspěvky") {
        UiStateScreen(state) { data ->
            Box(modifier = Modifier.fillMaxSize().padding(it)) {
                Posts(
                    posts = data,
                    Modifier
                        .fillMaxSize()
                ) { post ->
                    navigator.push(PostDetail(post.id))
                }
            }
        }
    }
}

@Composable
private fun Posts(
    posts: List<Post>,
    modifier: Modifier = Modifier,
    clickListener: (Post) -> Unit
) {
    LazyColumn(modifier) {
        items(items = posts) { post ->
            Post(item = post, clickListener = clickListener)
            Divider()
        }
    }
}

@Composable
private fun Post(item: Post, clickListener: (Post) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .clickable {
            clickListener(item)
        }
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}