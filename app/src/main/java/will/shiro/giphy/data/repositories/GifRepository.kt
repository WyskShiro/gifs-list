package will.shiro.giphy.data.repositories

import will.shiro.giphy.data.datasources.IRemoteGifDataSource
import will.shiro.giphy.data.models.ApiGif
import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.domain.repositories.IGifRepository
import will.shiro.giphy.gifs.home.models.UIGifModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GifRepository @Inject constructor(
    private val remoteGifDataSource: IRemoteGifDataSource
) : IGifRepository {
    private var selectedGif: UIGifModel? = null

    override suspend fun getRandom(): Gif {
        return ApiGif.toDomain(remoteGifDataSource.getRandom())
    }

    override suspend fun getSearch(search: String): List<Gif> {
        return remoteGifDataSource.getSearch(search).map(ApiGif::toDomain)
    }

    override fun getSelected(): UIGifModel {
        return selectedGif!!
    }

    override fun setSelected(gif: UIGifModel) {
        selectedGif = gif
    }
}