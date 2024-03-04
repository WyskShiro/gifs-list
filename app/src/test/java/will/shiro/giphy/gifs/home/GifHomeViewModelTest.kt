package will.shiro.giphy.gifs.home

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import will.shiro.giphy.MainCoroutineRule
import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.domain.usecases.GetRandomGifUseCase
import will.shiro.giphy.domain.usecases.GetSearchGifUseCase
import will.shiro.giphy.gifs.home.models.UIGifHome
import will.shiro.giphy.gifs.home.models.UIGifHomeModel

@ExperimentalCoroutinesApi
class GifHomeViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getSearchGifUseCase: GetSearchGifUseCase

    @MockK
    private lateinit var getRandomGifUseCase: GetRandomGifUseCase
    private lateinit var viewModel: GifHomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel =
            GifHomeViewModel(
                getRandomGifUseCase,
                getSearchGifUseCase
            )
    }

    @Test
    fun testGetRandomGifSuccess() = runTest {
        coEvery { getRandomGifUseCase() } returns Gif(
            url = "url",
            title = "title",
            link = "link",
            rating = "rating"
        )
        viewModel.getRandomGif(false)
        advanceUntilIdle()

        assertEquals(
            viewModel.state.value,
            UIGifHome.State(
                gif = UIGifHomeModel(
                    url = "url",
                    title = "title",
                    link = "link",
                    rating = "rating"
                )
            )
        )
    }

    @Test
    fun testGetSearchGifsSuccess() = runTest {
        coEvery { getSearchGifUseCase("asdf") } returns listOf(
            Gif(
                url = "url",
                title = "title",
                link = "link",
                rating = "rating"
            )
        )
        viewModel.getSearchGifs("asdf")
        advanceUntilIdle()

        assertEquals(
            viewModel.state.value,
            UIGifHome.State(
                searchText = "asdf",
                searchGifs = listOf(
                    UIGifHomeModel(
                        url = "url",
                        title = "title",
                        link = "link",
                        rating = "rating"
                    )
                )
            )
        )
    }
}