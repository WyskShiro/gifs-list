package will.shiro.giphy.gifs.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import will.shiro.giphy.gifs.components.GifDetailsComposable
import will.shiro.giphy.gifs.components.LoadingComposable
import will.shiro.giphy.gifs.home.components.GifHomeSearchTextComposable
import will.shiro.giphy.gifs.home.models.UIGifHome
import will.shiro.giphy.theme.BackgroundBlack

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun GifHomeScreen(
    onSearchTextClick: () -> Unit,
    viewModel: GifHomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val sideEffect by viewModel.sideEffect.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = BackgroundBlack
    ) {
        HomeRandomGifComposable(
            uiState,
            sideEffect,
            onSearchTextClick
        )
    }
}

@Composable
internal fun HomeRandomGifComposable(
    uiState: UIGifHome.State,
    sideEffect: UIGifHome.SideEffect,
    onSearchTextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
        GifHomeSearchTextComposable(onSearchTextClick)
        when (sideEffect) {
            UIGifHome.SideEffect.Initial -> return
            is UIGifHome.SideEffect.Loading -> if (!sideEffect.isLoading && uiState.gif != null) {
                GifDetailsComposable(uiState.gif)
            } else {
                LoadingComposable()
            }
        }
    }
}
