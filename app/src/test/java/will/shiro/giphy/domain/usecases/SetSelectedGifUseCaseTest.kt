package will.shiro.giphy.domain.usecases

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.justRun
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import will.shiro.giphy.domain.repositories.IGifRepository
import will.shiro.giphy.gifs.home.models.UIGifModel

class SetSelectedGifUseCaseTest {
    @MockK
    lateinit var repository: IGifRepository
    lateinit var useCase: SetSelectedGifUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = SetSelectedGifUseCase(repository)
    }

    @Test
    fun `GIVEN repository success WHEN useCase called THEN set gif`() = runBlocking {
        // GIVEN
        val gif = UIGifModel(
            "asdf",
            "asdf",
            "asdf",
            "asdf"
        )
        justRun { repository.setSelected(gif) }
        // WHEN
        useCase(gif)
        // THEN
        verify(exactly = 1) { repository.setSelected(gif) }
    }
}