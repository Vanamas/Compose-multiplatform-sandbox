package presentation.screen.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import dev.icerock.moko.media.compose.BindMediaPickerEffect
import dev.icerock.moko.media.compose.rememberMediaPickerControllerFactory
import dev.icerock.moko.media.compose.toImageBitmap
import dev.icerock.moko.media.picker.MediaSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import presentation.screen.common.BackButtonScaffold

@Composable
fun ImageChooserScreen(screenModel: ImageChooserViewModel) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val mediaFactory = rememberMediaPickerControllerFactory()
    val picker = remember(mediaFactory) { mediaFactory.createMediaPickerController() }

    BackButtonScaffold("Image chooser") { padding ->
        BindMediaPickerEffect(picker)

        var image: ImageBitmap? by remember { mutableStateOf(null) }

        Column(
            Modifier.fillMaxWidth().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            image?.let {
                Image(bitmap = it, contentDescription = null)
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                        val result = picker.pickImage(MediaSource.GALLERY)
                        image = result.toImageBitmap()
                    }
                }
            ) {
                Text(text = "Pick an image from gallery")
            }
            Spacer(Modifier.size(16.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        val result = picker.pickImage(MediaSource.CAMERA)
                        image = result.toImageBitmap()
                    }
                }
            ) {
                Text(text = "Take a picture")
            }
        }
    }
}