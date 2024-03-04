package will.shiro.giphy.data.services

import retrofit2.http.GET
import retrofit2.http.Query
import will.shiro.giphy.BuildConfig
import will.shiro.giphy.data.models.ApiGif
import will.shiro.giphy.data.models.ApiGifWrapper

interface RemoteGifService {

    @GET("gifs/random")
    suspend fun getRandomGif(@Query(value = "api_key") apiKey: String = BuildConfig.GIPHY_API_KEY): ApiGifWrapper<ApiGif>

    @GET("gifs/search")
    suspend fun getSearchGifs(
        @Query(value = "api_key") apiKey: String = BuildConfig.GIPHY_API_KEY,
        @Query(value = "q") query: String,
        @Query(value = "limit") limit: Int = DEFAULT_LIMIT_PAGE,
        @Query(value = "offset") offset: Int = DEFAULT_OFFSET_PAGE
    ): ApiGifWrapper<List<ApiGif>>
}

const val DEFAULT_LIMIT_PAGE = 25
const val DEFAULT_OFFSET_PAGE = 0
