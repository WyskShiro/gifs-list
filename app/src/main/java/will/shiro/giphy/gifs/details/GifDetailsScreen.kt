package will.shiro.giphy.gifs.details

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import will.shiro.giphy.gifs.components.GifDetailsComposable
import will.shiro.giphy.theme.BackgroundBlack

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun GifDetailsScreen(
    viewModel: GifDetailsViewModel = hiltViewModel()
) {
    val gifSelected by viewModel.gifSelected.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = BackgroundBlack
    ) {
        GifDetailsComposable(gifSelected)
    }
}
