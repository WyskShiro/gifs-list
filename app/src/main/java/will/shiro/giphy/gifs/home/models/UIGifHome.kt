package will.shiro.giphy.gifs.home.models

sealed interface UIGifHome {
    data class State(
        val gif: UIGifModel? = null,
    ) : UIGifHome

    sealed class SideEffect : UIGifHome {
        data object Initial : SideEffect()
        data class Loading(val isLoading: Boolean) : SideEffect()
    }
}