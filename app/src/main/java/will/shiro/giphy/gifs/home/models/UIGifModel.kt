package will.shiro.giphy.gifs.home.models

import android.content.res.Resources
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import will.shiro.giphy.R
import will.shiro.giphy.domain.models.Gif

@Parcelize
data class UIGifModel(
    val url: String,
    val title: String,
    val link: String,
    val rating: String
) : Parcelable {
    companion object {
        fun fromGif(gif: Gif, resources: Resources): UIGifModel {
            val rating = when (gif.rating.uppercase()) {
                "G" -> resources.getString(R.string.general_audiences_rating)
                "PG" -> resources.getString(R.string.pg_parental_guidance_rating)
                "PG-13" -> resources.getString(R.string.pg_13_parents_strongly_rating)
                "R" -> resources.getString(R.string.r_restricted_rating)
                else -> resources.getString(R.string.not_rated_rating)
            }
            return UIGifModel(
                url = gif.url,
                title = gif.title,
                link = gif.link,
                rating = rating,
            )
        }
    }
}