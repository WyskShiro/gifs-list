package will.shiro.giphy.gifs.search

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import will.shiro.giphy.domain.usecases.GetSearchGifUseCase
import will.shiro.giphy.domain.usecases.SetSelectedGifUseCase
import will.shiro.giphy.gifs.home.models.UIGifModel
import will.shiro.giphy.gifs.search.models.UIGifSearch
import javax.inject.Inject

@HiltViewModel
class GifSearchViewModel @Inject constructor(
    private val getSearchGifUseCase: GetSearchGifUseCase,
    private val setSelectedGifUseCase: SetSelectedGifUseCase,
    private val resources: Resources
) : ViewModel() {
    private val _state = MutableStateFlow(UIGifSearch.State())
    private val _sideEffect: MutableStateFlow<UIGifSearch.SideEffect> =
        MutableStateFlow(UIGifSearch.SideEffect.Initial)
    val state: StateFlow<UIGifSearch.State> = _state
    val sideEffect: StateFlow<UIGifSearch.SideEffect> = _sideEffect
    private var searchJob: Job? = null

    fun handleSearchText(search: String) {
        searchJob?.cancel()
        if (search.isNotEmpty()) {
            getSearchGifs(search)
        }
    }

    fun getSearchGifs(search: String) {
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE)
            if (search.length >= MINIMUM_CHARS_TO_SEARCH) {
                _sideEffect.emit(UIGifSearch.SideEffect.Loading(isLoading = true))
                try {
                    val gifsResult = getSearchGifUseCase(search)
                    _state.emit(
                        _state.value.copy(
                            searchGifs = gifsResult.map {
                                UIGifModel.fromGif(
                                    it,
                                    resources
                                )
                            },
                            searchText = search
                        )
                    )
                } catch (e: Exception) {
                    // TODO handle errors
                }
                _sideEffect.emit(UIGifSearch.SideEffect.Loading(isLoading = false))
            }
        }
    }

    fun saveGifClick(gif: UIGifModel) {
        setSelectedGifUseCase(gif)
    }
}

const val SEARCH_DEBOUNCE = 500L
const val MINIMUM_CHARS_TO_SEARCH = 2