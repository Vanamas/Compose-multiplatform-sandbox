package presentation.screen.post

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import domain.model.Post
import domain.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.screen.common.model.UiState

/**
 * Posts view model.
 *
 * @author Martin Vana
 */
class PostsViewModel(private val getPostsUseCase: GetPostsUseCase) :
    StateScreenModel<UiState<List<Post>>>(UiState.Loading) {

    init {
        reload()
    }

    private fun reload(forceReload: Boolean = false) {
        coroutineScope.launch {
            mutableState.value = runCatching {
                getPostsUseCase(forceReload)
            }.fold(
                onSuccess = { UiState.Success(it) },
                onFailure = { UiState.Error(it.message ?: "Uknown error") { reload() } }
            )
        }
    }
}