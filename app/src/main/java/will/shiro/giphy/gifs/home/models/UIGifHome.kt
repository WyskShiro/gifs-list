package will.shiro.giphy.gifs.home.models

sealed interface UIGifHome {
    data class State(
        val gif: UIGifHomeModel? = null,
        val searchText: String = "",
        val searchGifs: List<UIGifHomeModel> = listOf(),
    ) : UIGifHome

    sealed class SideEffect : UIGifHome {
        data object Initial : SideEffect()
        data class Loading(val isLoading: Boolean) : SideEffect()
    }
}