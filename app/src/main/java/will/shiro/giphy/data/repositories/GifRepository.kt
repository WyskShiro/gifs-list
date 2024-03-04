package will.shiro.giphy.data.repositories

import will.shiro.giphy.data.datasources.IRemoteGifDataSource
import will.shiro.giphy.data.models.ApiGif
import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.domain.repositories.IGifRepository
import javax.inject.Inject

class GifRepository @Inject constructor(
    private val remoteGifDataSource: IRemoteGifDataSource
) : IGifRepository {
    override suspend fun getRandom(): Gif {
        return ApiGif.toDomain(remoteGifDataSource.getRandom())
    }

    override suspend fun getSearch(search: String): List<Gif> {
        return remoteGifDataSource.getSearch(search).map(ApiGif::toDomain)
    }
}