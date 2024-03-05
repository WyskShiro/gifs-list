package will.shiro.giphy.domain.repositories

import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.gifs.home.models.UIGifModel

interface IGifRepository {

    suspend fun getRandom(): Gif
    suspend fun getSearch(search: String): List<Gif>
    fun getSelected(): UIGifModel
    fun setSelected(gif: UIGifModel)
}