package will.shiro.giphy.gifs.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import will.shiro.giphy.domain.repositories.IGifRepository
import will.shiro.giphy.gifs.home.models.UIGifModel
import javax.inject.Inject

@HiltViewModel
class GifDetailsViewModel @Inject constructor(
    private val gifRepository: IGifRepository,
) : ViewModel() {
    private val _gifSelected = MutableStateFlow(gifRepository.getSelected())
    val gifSelected: StateFlow<UIGifModel> = _gifSelected
}

