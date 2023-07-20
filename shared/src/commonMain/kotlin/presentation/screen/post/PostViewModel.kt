package presentation.screen.post

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import domain.model.Post
import domain.usecase.GetPostUseCase
import kotlinx.coroutines.launch
import presentation.screen.common.model.UiState

/**
 * Post detail view model.
 *
 * @author Martin Vana
 */
class PostViewModel(
    private val postId: Long,
    private val getPostUseCase: GetPostUseCase
) : StateScreenModel<UiState<Post>>(UiState.Loading) {

    init {
        reload()
    }

    private fun reload() {
        coroutineScope.launch {
            mutableState.value = UiState.Loading

            mutableState.value = runCatching {
                getPostUseCase(postId)
            }.fold(
                onSuccess = { UiState.Success(it) },
                onFailure = { UiState.Error(it.message ?: "Unknown error") { reload() } }
            )
        }
    }
}