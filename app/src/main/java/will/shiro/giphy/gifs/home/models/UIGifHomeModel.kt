package will.shiro.giphy.gifs.home.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import will.shiro.giphy.domain.models.Gif

@Parcelize
data class UIGifHomeModel(
    val url: String,
    val title: String,
    val link: String,
    val rating: String
) : Parcelable {
    companion object {
        fun fromGif(gif: Gif): UIGifHomeModel {
            val rating = when (gif.rating.uppercase()) {
                "G" -> "G - General Audiences - Level 1"
                "PG" -> "PG - Parental Guidance Suggested - Level 2"
                "PG-13" -> "PG-13 - Parents Strongly Cautioned - Level 3"
                "R" -> "R - Restricted - Level 4"
                else -> "Not Rated"
            }
            return UIGifHomeModel(
                url = gif.url,
                title = gif.title,
                link = gif.link,
                rating = rating,
            )
        }
    }
}