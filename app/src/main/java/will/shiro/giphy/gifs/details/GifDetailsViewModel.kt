package will.shiro.giphy.gifs.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import will.shiro.giphy.domain.usecases.GetSelectedGifUseCase
import will.shiro.giphy.gifs.home.models.UIGifModel
import javax.inject.Inject

@HiltViewModel
class GifDetailsViewModel @Inject constructor(
    getSelectedGifUseCase: GetSelectedGifUseCase,
) : ViewModel() {
    private val _gifSelected = MutableStateFlow(getSelectedGifUseCase())
    val gifSelected: StateFlow<UIGifModel> = _gifSelected
}

