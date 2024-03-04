package will.shiro.giphy.gifs.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import will.shiro.giphy.domain.usecases.GetRandomGifUseCase
import will.shiro.giphy.domain.usecases.GetSearchGifUseCase
import will.shiro.giphy.gifs.home.models.UIGifHome
import will.shiro.giphy.gifs.home.models.UIGifHomeModel
import javax.inject.Inject

@HiltViewModel
class GifHomeViewModel @Inject constructor(
    private val getRandomGifUseCase: GetRandomGifUseCase,
    private val getSearchGifUseCase: GetSearchGifUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UIGifHome.State())
    private val _sideEffect: MutableStateFlow<UIGifHome.SideEffect> =
        MutableStateFlow(UIGifHome.SideEffect.Initial)
    val state: StateFlow<UIGifHome.State> = _state
    val sideEffect: StateFlow<UIGifHome.SideEffect> = _sideEffect
    private var randomGifJob: Job? = null
    private var searchJob: Job? = null

    fun setUp() {
        getRandomGif()
    }

    fun handleSearchText(search: String) {
        searchJob?.cancel()
        _state.value.copy(
            searchText = search
        )
        if (search.isNotEmpty()) {
            randomGifJob?.cancel()
            getSearchGifs(search)
        }
    }

    fun getSearchGifs(search: String) {
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE)
            if (search.length >= MINIMUM_CHARS_TO_SEARCH) {
                _sideEffect.emit(UIGifHome.SideEffect.Loading(isLoading = true))
                try {
                    val gifsResult = getSearchGifUseCase(search)
                    _state.emit(
                        _state.value.copy(
                            searchGifs = gifsResult.map(UIGifHomeModel::fromGif),
                            searchText = search
                        )
                    )
                } catch (e: Exception) {
                    // TODO handle errors
                }
                _sideEffect.emit(UIGifHome.SideEffect.Loading(isLoading = false))
            }
        }
    }

    fun getRandomGif(scheduleNewRandomGif: Boolean = true) {
        randomGifJob = viewModelScope.launch {
            _sideEffect.emit(UIGifHome.SideEffect.Loading(isLoading = true))
            try {
                val gifResult = getRandomGifUseCase()
                _state.emit(
                    _state.value.copy(
                        gif = UIGifHomeModel.fromGif(gifResult),
                        searchGifs = listOf(),
                        searchText = ""
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
const val SEARCH_DEBOUNCE = 500L
const val MINIMUM_CHARS_TO_SEARCH = 2