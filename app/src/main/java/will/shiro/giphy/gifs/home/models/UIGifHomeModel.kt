package will.shiro.giphy.gifs.home.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIGifHomeModel(
    val url: String,
    val title: String,
    val link: String,
    val rating: String
) : Parcelable