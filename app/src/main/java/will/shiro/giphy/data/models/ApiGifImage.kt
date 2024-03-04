package will.shiro.giphy.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiGifImage(
    @field:Json(name = "url")
    val url: String?
)