package will.shiro.giphy.domain.repositories

import will.shiro.giphy.domain.models.Gif

interface IGifRepository {

    suspend fun getRandom(): Gif
    suspend fun getSearch(search: String): List<Gif>
}