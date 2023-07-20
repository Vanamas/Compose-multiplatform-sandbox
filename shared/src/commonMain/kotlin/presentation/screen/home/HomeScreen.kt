package presentation.screen.home

import Test
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import dev.icerock.moko.media.compose.BindMediaPickerEffect
import dev.icerock.moko.media.compose.rememberMediaPickerControllerFactory
import dev.icerock.moko.media.compose.toImageBitmap
import dev.icerock.moko.media.picker.MediaSource
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import getPlatformName
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.screen.common.AppDrawerItemInfo
import presentation.screen.common.DrawerScaffold
import presentation.screen.common.model.Components
import presentation.screen.common.model.Home
import presentation.screen.common.model.ImageChooser
import presentation.screen.common.model.Posts

@Composable
fun HomeScreen(screenModel: HomeViewModel) {

    DrawerScaffold("Top bar", { Text(text = "Bottom Bar") }, {}, DrawerParams.drawerButtons) {
        Content()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Content() {
    var greetingText by remember { mutableStateOf("Hello, World!") }
    var showImage by remember { mutableStateOf(false) }
    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val controller: PermissionsController = remember(factory) { factory.createPermissionsController() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Test()

        Button(onClick = {
            greetingText = "Hello, ${getPlatformName()}"
            showImage = !showImage
        }) {
            Text(greetingText)
        }
        AnimatedVisibility(showImage) {
            Image(
                painterResource("compose-multiplatform.xml"),
                null
            )
        }
        Button(
            onClick = {
                coroutineScope.launch {
                    val permission = Permission.LOCATION
                    controller.providePermission(permission)
                    try {
                        controller.getPermissionState(permission)
                            .also { println("pre provide $it") }

                        // Calls suspend function in a coroutine to request some permission.
                        controller.providePermission(permission)
                        // If there are no exceptions, permission has been granted successfully.

                    } catch (deniedAlwaysException: DeniedAlwaysException) {
                        Napier.d { "denied always" }
                    } catch (deniedException: DeniedException) {
                        Napier.d { "denied" }
                    } finally {
                        controller.getPermissionState(permission)
                            .also { println("post provide $it") }
                    }
                }
            }
        ) {
            Text(text = "give permissions")
        }
    }
}

// list of the buttons
private object DrawerParams {
    val drawerButtons = arrayListOf(
        AppDrawerItemInfo(
            Home,
            "Home",
            Icons.Filled.Home
        ),
        AppDrawerItemInfo(
            Posts,
            "Posts",
            Icons.Filled.PostAdd
        ),
        AppDrawerItemInfo(
            ImageChooser,
            "Image chooser",
            Icons.Filled.Image
        ),
        AppDrawerItemInfo(
            Components,
            "Components",
            Icons.Filled.Compare
        ),
    )
}