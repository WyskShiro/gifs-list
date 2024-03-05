package will.shiro.giphy.gifs.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import will.shiro.giphy.R

@Composable
internal fun GifHomeSearchTextComposable(onClick: () -> Unit) {
    Text(
        text = stringResource(id = R.string.search),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(16.dp)
            .clickable(onClick = onClick)
    )
}