package will.shiro.giphy.gifs.search.models

import will.shiro.giphy.gifs.home.models.UIGifModel

sealed interface UIGifSearch {
    data class State(
        val searchText: String = "",
        val searchGifs: List<UIGifModel> = listOf(),
    ) : UIGifSearch

    sealed class SideEffect : UIGifSearch {
        data object Initial : SideEffect()
        data class Loading(val isLoading: Boolean) : SideEffect()
    }
}