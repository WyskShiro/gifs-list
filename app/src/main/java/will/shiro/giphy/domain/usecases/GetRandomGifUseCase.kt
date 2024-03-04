package will.shiro.giphy.domain.usecases

import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.domain.repositories.IGifRepository
import javax.inject.Inject

class GetRandomGifUseCase @Inject constructor(
    private val gifRepository: IGifRepository
) {
    suspend operator fun invoke(): Gif {
        return gifRepository.getRandom()
    }
}