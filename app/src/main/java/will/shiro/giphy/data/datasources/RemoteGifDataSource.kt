package will.shiro.giphy.data.datasources

import will.shiro.giphy.data.models.ApiGif
import will.shiro.giphy.data.services.RemoteGifService
import javax.inject.Inject

interface IRemoteGifDataSource {
    suspend fun getRandom(): ApiGif
    suspend fun getSearch(search: String): List<ApiGif>
}

class RemoteGifDataSource @Inject constructor(
    private val remoteGifService: RemoteGifService
) : IRemoteGifDataSource {
    override suspend fun getRandom(): ApiGif {
        return remoteGifService.getRandomGif().data
    }

    override suspend fun getSearch(search: String): List<ApiGif> {
        return remoteGifService.getSearchGifs(query = search).data
    }
}