package will.shiro.giphy.domain.usecases

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.domain.repositories.IGifRepository

class GetRandomGifUseCaseTest {

    @MockK
    lateinit var repository: IGifRepository
    lateinit var useCase: GetRandomGifUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetRandomGifUseCase(repository)
    }

    @Test
    fun `GIVEN repository success WHEN useCase called THEN return gif`() = runBlocking {
        // GIVEN
        val result = mockk<Gif>()
        coEvery { repository.getRandom() } returns result
        // WHEN
        val gif = useCase()
        // THEN
        assertEquals(
            result,
            gif
        )
    }

    @Test
    fun `GIVEN repository error WHEN useCase called THEN throw Exception`() = runBlocking {
        // GIVEN
        coEvery { repository.getRandom() } throws RuntimeException()

        // WHEN
        val result = runCatching {
            useCase()
        }.onFailure {
            assertTrue(it is java.lang.RuntimeException)
        }

        // THEN
        assertTrue(result.isFailure)
    }
}