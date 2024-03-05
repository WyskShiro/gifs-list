package will.shiro.giphy.gifs.search.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import will.shiro.giphy.gifs.components.GifComposable
import will.shiro.giphy.gifs.home.models.UIGifModel

@Composable
internal fun GifSearchListComposable(gifs: List<UIGifModel>, onGifClick: (UIGifModel) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3)
    ) {
        items(gifs) { gif ->
            GifComposable(
                gif = gif,
                onGifClick = onGifClick
            )
        }
    }
}