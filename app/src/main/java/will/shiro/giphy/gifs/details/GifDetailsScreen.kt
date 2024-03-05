package will.shiro.giphy.gifs.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import will.shiro.giphy.R
import will.shiro.giphy.gifs.components.GifDetailsComposable
import will.shiro.giphy.theme.BackgroundBlack

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun GifDetailsScreen(
    onBackClick: () -> Unit,
    viewModel: GifDetailsViewModel = hiltViewModel()
) {
    val gifSelected by viewModel.gifSelected.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = BackgroundBlack
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back_24),
                contentDescription = stringResource(id = R.string.back_button),
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp
                    )
                    .clickable { onBackClick() }
            )
            GifDetailsComposable(gifSelected)
        }
    }
}
