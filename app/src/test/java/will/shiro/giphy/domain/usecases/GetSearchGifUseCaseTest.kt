package will.shiro.giphy.domain.usecases

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.domain.repositories.IGifRepository

class GetSearchGifUseCaseTest {

    @MockK
    lateinit var repository: IGifRepository
    lateinit var useCase: GetSearchGifUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetSearchGifUseCase(repository)
    }

    @Test
    fun `GIVEN repository success WHEN useCase called THEN return gifs`() = runBlocking {
        // GIVEN
        val result = listOf<Gif>()
        coEvery { repository.getSearch("asdf") } returns result
        // WHEN
        val gif = useCase("asdf")
        // THEN
        assertEquals(
            result,
            gif
        )
    }

    @Test
    fun `GIVEN repository error WHEN useCase called THEN throw Exception`() = runBlocking {
        // GIVEN
        coEvery { repository.getSearch("asdf") } throws RuntimeException()

        // WHEN
        val result = runCatching {
            useCase("asdf")
        }.onFailure {
            assertTrue(it is java.lang.RuntimeException)
        }

        // THEN
        assertTrue(result.isFailure)
    }
}