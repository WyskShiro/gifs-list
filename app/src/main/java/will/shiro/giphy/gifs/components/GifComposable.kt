package will.shiro.giphy.gifs.components

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import will.shiro.giphy.gifs.home.models.UIGifModel

@Composable
internal fun GifComposable(gif: UIGifModel, onGifClick: (UIGifModel) -> Unit = {}) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context).components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gif.url).apply(block = {
                size(Size.ORIGINAL)
            }).build(),
            imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onGifClick(gif) }
            .testTag("GifComposableTest_${gif.url}")
    )
}