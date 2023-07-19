package presentation.screen.home

import cafe.adriel.voyager.core.model.ScreenModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.model.Post
import domain.usecase.GetPostsUseCase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.screen.common.model.UiState

/**
 * View model of main view
 *
 * @author Martin Vana
 */
class HomeViewModel : ScreenModel {

}