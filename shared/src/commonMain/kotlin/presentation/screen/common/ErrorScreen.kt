package presentation.screen.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A [Composable] function that displays an error screen with a message and a retry button.
 *
 * The error message is centered on the screen and the retry button is placed below it.
 * The error message and the retry button are separated by a spacer of a base padding size.
 *
 * The error message is passed as a string resource ID, which is resolved to a string using [stringResource].
 * When the retry button is clicked, it executes the [onRetry] lambda.
 *
 * @param errorMessage The string resource ID of the error message to display.
 * @param onRetry A lambda that is executed when the retry button is clicked.
 */
@Composable
fun ErrorScreen(errorMessage: String, onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        SelectionContainer {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRetry) {
            Text(text = "Try again")
        }
    }
}