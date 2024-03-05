package will.shiro.giphy.domain.usecases

import will.shiro.giphy.domain.repositories.IGifRepository
import will.shiro.giphy.gifs.home.models.UIGifModel
import javax.inject.Inject

class GetSelectedGifUseCase @Inject constructor(
    private val gifRepository: IGifRepository
) {
    operator fun invoke(): UIGifModel {
        return gifRepository.getSelected()
    }
}