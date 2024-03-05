package will.shiro.giphy.gifs.home

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import will.shiro.giphy.domain.usecases.GetRandomGifUseCase
import will.shiro.giphy.gifs.home.models.UIGifHome
import will.shiro.giphy.gifs.home.models.UIGifModel
import javax.inject.Inject

@HiltViewModel
class GifHomeViewModel @Inject constructor(
    private val getRandomGifUseCase: GetRandomGifUseCase,
    private val resources: Resources
) : ViewModel() {
    private val _state = MutableStateFlow(UIGifHome.State())
    private val _sideEffect: MutableStateFlow<UIGifHome.SideEffect> =
        MutableStateFlow(UIGifHome.SideEffect.Initial)
    val state: StateFlow<UIGifHome.State> = _state
    val sideEffect: StateFlow<UIGifHome.SideEffect> = _sideEffect
    private var randomGifJob: Job? = null

    @VisibleForTesting
    var scheduleNewRandomGif = true

    init {
        getRandomGif()
    }

    fun getRandomGif() {
        randomGifJob = viewModelScope.launch {
            _sideEffect.emit(UIGifHome.SideEffect.Loading(isLoading = true))
            try {
                val gifResult = getRandomGifUseCase()
                _state.emit(
                    _state.value.copy(
                        gif = UIGifModel.fromGif(
                            gifResult,
                            resources
                        ),
                    )
                )
            } catch (e: Exception) {
                // TODO handle errors
            }
            _sideEffect.emit(UIGifHome.SideEffect.Loading(isLoading = false))
            if (scheduleNewRandomGif) {
                delay(DELAY_NEW_RANDOM_GIF)
                getRandomGif()
            }
        }
    }
}

const val DELAY_NEW_RANDOM_GIF = 10000L
