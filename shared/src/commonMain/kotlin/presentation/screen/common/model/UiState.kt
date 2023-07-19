package presentation.screen.common.model

import presentation.screen.common.model.UiState.Error
import presentation.screen.common.model.UiState.Loading
import presentation.screen.common.model.UiState.Success

/**
 * A sealed interface that represents different states of the UI.
 *
 * @param T The type of data which is delivered in the [Success] state.
 * @see Loading
 * @see Success
 * @see Error
 */
sealed interface UiState<out T> {
    /**
     * Represents the loading state, indicating that some operation is in progress.
     */
    object Loading : UiState<Nothing>

    /**
     * Represents the success state, delivering the resulting data [T].
     *
     * @property data The data of type [T] resulting from the successful operation.
     */
    data class Success<out T>(val data: T) : UiState<T>

    /**
     * Represents the error state, delivering an error message and a retry action.
     *
     * @property errorMessage The string resource ID of the error message to be displayed.
     * @property retryAction A function to be invoked when retry action is performed.
     */
    data class Error(val errorMessage: String, val retryAction: () -> Unit) :
        UiState<Nothing>
}