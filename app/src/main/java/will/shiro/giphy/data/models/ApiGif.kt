package will.shiro.giphy.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.utils.exceptions.InvalidValueException
import java.lang.NullPointerException

@JsonClass(generateAdapter = true)
data class ApiGif(
    @field:Json(name = "images")
    val images: ApiGifImages?,

    @field:Json(name = "bitly_gif_url")
    val bitlyGifUrl: String?,

    @field:Json(name = "rating")
    val rating: String?,

    @field:Json(name = "title")
    val title: String?
) {
    companion object {
        fun toDomain(apiGif: ApiGif): Gif {
            return with(apiGif) {
                try {
                    Gif(
                        url = images!!.original!!.url!!,
                        link = bitlyGifUrl!!,
                        rating = rating!!,
                        title = title!!
                    )
                } catch (e: NullPointerException) {
                    throw InvalidValueException()
                }
            }
        }
    }
}