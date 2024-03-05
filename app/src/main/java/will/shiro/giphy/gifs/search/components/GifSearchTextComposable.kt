package will.shiro.giphy.gifs.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import will.shiro.giphy.R

@Composable
internal fun GifSearchTextComposable(
    onValueChange: (String) -> Unit = {},
    onBackClick: () -> Unit = {},
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back_24),
            contentDescription = "Back button",
            modifier = Modifier.clickable { onBackClick() }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                ),
            value = value,
            onValueChange = onValueChange,
            label = { Text(stringResource(id = R.string.search)) },
        )
    }
}