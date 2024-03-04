package will.shiro.giphy.domain.usecases

import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.domain.repositories.IGifRepository
import javax.inject.Inject

class GetSearchGifUseCase @Inject constructor(
    private val gifRepository: IGifRepository
) {
    suspend operator fun invoke(search: String): List<Gif> {
        return gifRepository.getSearch(search)
    }
}