package presentation.screen.post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.Post
import presentation.screen.common.BackButtonScaffold
import presentation.screen.common.UiStateScreen

/**
 * Post detail screen.
 *
 * @author Martin Vana
 */
@Composable
fun PostDetailScreen(
    viewModel: PostViewModel
) {
    val state = viewModel.state.collectAsState()
    var title by remember { mutableStateOf("") }

    BackButtonScaffold(title, {
        IconButton(onClick = {/* Do Something*/ }) {
            Icon(Icons.Filled.Share, null)
        }
    }) { padding ->
        UiStateScreen(uiState = state.value) {
            title = it.title
            Post(
                it,
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun Post(post: Post, modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        post.body?.let { text -> Text(text = text) }
    }
}