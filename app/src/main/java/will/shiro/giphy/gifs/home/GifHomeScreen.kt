package will.shiro.giphy.gifs.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import will.shiro.giphy.gifs.components.GifDetailsComposable
import will.shiro.giphy.gifs.components.LoadingComposable
import will.shiro.giphy.gifs.home.models.UIGifHome

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun GifHomeScreen(
    viewModel: GifHomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val sideEffect by viewModel.sideEffect.collectAsStateWithLifecycle()

    viewModel.setUp()

    Scaffold(
        containerColor = Color.Cyan
    ) {
        HomeRandomGifComposable(
            uiState,
            sideEffect
        )
    }
}

@Composable
internal fun HomeRandomGifComposable(uiState: UIGifHome.State, sideEffect: UIGifHome.SideEffect) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
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
