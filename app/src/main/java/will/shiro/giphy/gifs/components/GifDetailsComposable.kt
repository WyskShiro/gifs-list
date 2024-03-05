package will.shiro.giphy.gifs.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import will.shiro.giphy.R
import will.shiro.giphy.gifs.home.models.UIGifHomeModel

@Composable
internal fun GifDetailsComposable(gif: UIGifHomeModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        GifComposable(gif = gif)
        Text(
            text = stringResource(id = R.string.title_label_name),
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = gif.title,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = stringResource(id = R.string.link_label_name),
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = gif.link,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = gif.rating,
            modifier = Modifier.padding(top = 16.dp).border(
                brush = Brush.horizontalGradient(
                    listOf( // TODO change colors based on rating
                        Color.Green,
                        Color.Blue
                    )
                ),
                width = 1.dp,
                shape = CircleShape
            ).padding(4.dp)
        )
    }
}