package will.shiro.giphy.gifs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import will.shiro.giphy.theme.BackgroundBlack
import will.shiro.giphy.theme.IndicatorGreen

@Composable
internal fun LoadingComposable() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundBlack),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = IndicatorGreen,
            trackColor = BackgroundBlack,
        )
    }
}