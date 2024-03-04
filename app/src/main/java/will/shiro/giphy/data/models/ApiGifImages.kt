package will.shiro.giphy.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiGifImages(
    @field:Json(name = "original")
    val original: ApiGifImage?
)