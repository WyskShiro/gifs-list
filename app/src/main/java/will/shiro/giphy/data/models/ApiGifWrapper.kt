package will.shiro.giphy.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiGifWrapper<T>(
    @field:Json(name = "data")
    val data: T
)