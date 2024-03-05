package will.shiro.giphy.gifs.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import will.shiro.giphy.gifs.components.LoadingComposable
import will.shiro.giphy.gifs.search.components.GifSearchListComposable
import will.shiro.giphy.gifs.search.components.GifSearchTextComposable
import will.shiro.giphy.gifs.search.models.UIGifSearch
import will.shiro.giphy.theme.BackgroundBlack

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun GifSearchScreen(
    onBackClick: () -> Unit,
    viewModel: GifSearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val sideEffect by viewModel.sideEffect.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = BackgroundBlack
    ) {
        GifSearchComposable(
            uiState,
            sideEffect,
            viewModel::handleSearchText,
            onBackClick
        )
    }
}

@Composable
internal fun GifSearchComposable(
    uiState: UIGifSearch.State,
    sideEffect: UIGifSearch.SideEffect,
    onSearchTextChange: (String) -> Unit,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
        var text by remember { mutableStateOf("") }

        GifSearchTextComposable(
            onValueChange = {
                onSearchTextChange(it)
                text = it
            },
            onBackClick = onBackClick,
            value = text
        )

        when (sideEffect) {
            UIGifSearch.SideEffect.Initial -> return
            is UIGifSearch.SideEffect.Loading -> if (sideEffect.isLoading) {
                LoadingComposable()
            } else {
                GifSearchListComposable(uiState.searchGifs)
            }
        }
    }
}
