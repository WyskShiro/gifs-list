package will.shiro.giphy.gifs.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import will.shiro.giphy.gifs.home.models.UIGifHomeModel

@Composable
internal fun GifSearchComposable(gifs: List<UIGifHomeModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        items(gifs) { gif ->
            GifComposable(gif = gif)
        }
    }
}