package presentation.screen.common

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import presentation.screen.common.model.UiState

/**
 * A [Composable] function that displays different UI states. It handles three states: Loading, Success and Error.
 *
 * When [UiState] is [UiState.Loading], it displays a loading screen.
 * When [UiState] is [UiState.Success], it displays content specified by the [content] lambda.
 * When [UiState] is [UiState.Error], it displays an error screen with an option to retry.
 *
 * It uses [Crossfade] to transition between these states with an animation.
 *
 * @param T The type of the data to be displayed when [UiState] is [UiState.Success].
 * @param uiState The current [UiState]. This is used to determine which UI to display.
 * @param content A [Composable] lambda that describes how to display the data when [UiState] is [UiState.Success].
 *                This lambda receives the data as an input parameter.
 */
@Composable
fun <T> UiStateScreen(
    uiState: UiState<T>,
    content: @Composable (T) -> Unit
) {
    Crossfade(targetState = uiState) { currentUiState ->
        when (currentUiState) {
            is UiState.Loading -> LoadingScreen()
            is UiState.Success -> content(currentUiState.data)
            is UiState.Error -> ErrorScreen(
                errorMessage = currentUiState.errorMessage,
                onRetry = currentUiState.retryAction
            )
        }
    }
}