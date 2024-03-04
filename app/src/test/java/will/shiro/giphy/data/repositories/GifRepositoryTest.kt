package will.shiro.giphy.data.repositories

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import will.shiro.giphy.data.datasources.IRemoteGifDataSource
import will.shiro.giphy.data.models.ApiGif
import will.shiro.giphy.data.models.ApiGifImage
import will.shiro.giphy.data.models.ApiGifImages
import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.utils.exceptions.InvalidValueException
import java.lang.RuntimeException

class GifRepositoryTest {
    @MockK
    lateinit var dataSource: IRemoteGifDataSource
    lateinit var repository: GifRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = GifRepository(dataSource)
    }

    @Test
    fun `GIVEN dataSource success WHEN getSearch called THEN return gifs`() = runBlocking {
        // GIVEN
        val result = listOf(
            Gif(
                url = "url",
                title = "title",
                link = "bitlyGifUrl",
                rating = "rating"
            )
        )
        coEvery { dataSource.getSearch("asdf") } returns listOf(
            ApiGif(
                images = ApiGifImages(original = ApiGifImage(url = "url")),
                bitlyGifUrl = "bitlyGifUrl",
                rating = "rating",
                title = "title"
            )
        )
        // WHEN
        val gif = repository.getSearch("asdf")
        // THEN
        assertEquals(
            result,
            gif
        )
    }

    @Test
    fun `GIVEN dataSource success WITH invalid response WHEN getSearch called THEN throw Exception`() =
        runBlocking {
            // GIVEN
            coEvery { dataSource.getSearch("asdf") } returns listOf(
                ApiGif(
                    images = null,
                    bitlyGifUrl = null,
                    rating = "rating",
                    title = "title"
                )
            )
            // WHEN
            val result = runCatching {
                repository.getSearch("asdf")
            }.onFailure {
                assertTrue(it is InvalidValueException)
            }
            // THEN
            assertTrue(result.isFailure)
        }

    @Test
    fun `GIVEN dataSource throws Exception WHEN getSearch called THEN throw Exception`() =
        runBlocking {
            // GIVEN
            coEvery { dataSource.getSearch("asdf") } throws RuntimeException()
            // WHEN
            val result = runCatching {
                repository.getSearch("asdf")
            }.onFailure {
                assertTrue(it is RuntimeException)
            }
            // THEN
            assertTrue(result.isFailure)
        }


    @Test
    fun `GIVEN dataSource success WHEN getRandom called THEN return gif`() = runBlocking {
        // GIVEN
        val result = Gif(
                url = "url",
                title = "title",
                link = "bitlyGifUrl",
                rating = "rating"
            )
        coEvery { dataSource.getRandom() } returns ApiGif(
                images = ApiGifImages(original = ApiGifImage(url = "url")),
                bitlyGifUrl = "bitlyGifUrl",
                rating = "rating",
                title = "title"
            )
        // WHEN
        val gif = repository.getRandom()
        // THEN
        assertEquals(
            result,
            gif
        )
    }

    @Test
    fun `GIVEN dataSource success WITH invalid response WHEN getRandom called THEN throw Exception`() =
        runBlocking {
            // GIVEN
            coEvery { dataSource.getRandom() } returns ApiGif(
                    images = null,
                    bitlyGifUrl = null,
                    rating = "rating",
                    title = "title"
            )
            // WHEN
            val result = runCatching {
                repository.getRandom()
            }.onFailure {
                assertTrue(it is InvalidValueException)
            }
            // THEN
            assertTrue(result.isFailure)
        }

    @Test
    fun `GIVEN dataSource throws Exception WHEN getRandom called THEN throw Exception`() =
        runBlocking {
            // GIVEN
            coEvery { dataSource.getRandom() } throws RuntimeException()
            // WHEN
            val result = runCatching {
                repository.getRandom()
            }.onFailure {
                assertTrue(it is RuntimeException)
            }
            // THEN
            assertTrue(result.isFailure)
        }
}