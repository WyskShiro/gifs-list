package will.shiro.giphy.domain.usecases

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import will.shiro.giphy.domain.repositories.IGifRepository
import will.shiro.giphy.gifs.home.models.UIGifModel

class GetSelectedGifUseCaseTest {
    @MockK
    lateinit var repository: IGifRepository
    lateinit var useCase: GetSelectedGifUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetSelectedGifUseCase(repository)
    }

    @Test
    fun `GIVEN repository success WHEN useCase called THEN return gif`() = runBlocking {
        // GIVEN
        val gif = UIGifModel(
            "asdf",
            "asdf",
            "asdf",
            "asdf"
        )
        every { repository.getSelected() } returns gif
        // WHEN
        val result = useCase()
        // THEN
        assertEquals(
            gif,
            result
        )
    }
}