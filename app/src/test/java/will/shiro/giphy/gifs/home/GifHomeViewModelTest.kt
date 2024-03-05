package will.shiro.giphy.gifs.home

import android.content.res.Resources
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
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
import will.shiro.giphy.R
import will.shiro.giphy.domain.models.Gif
import will.shiro.giphy.domain.usecases.GetRandomGifUseCase
import will.shiro.giphy.gifs.home.models.UIGifHome
import will.shiro.giphy.gifs.home.models.UIGifModel

@ExperimentalCoroutinesApi
class GifHomeViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getRandomGifUseCase: GetRandomGifUseCase

    @MockK
    private lateinit var resources: Resources
    private lateinit var viewModel: GifHomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel =
            GifHomeViewModel(
                getRandomGifUseCase,
                resources
            )
    }

    @Test
    fun testGetRandomGifSuccess() = runTest {
        coEvery { getRandomGifUseCase() } returns Gif(
            url = "url",
            title = "title",
            link = "link",
            rating = "g"
        )
        every { resources.getString(R.string.general_audiences_rating) } returns "G - General Audiences - Level 1"

        viewModel.scheduleNewRandomGif = false
        viewModel.getRandomGif()

        advanceUntilIdle()

        assertEquals(
            viewModel.state.value,
            UIGifHome.State(
                gif = UIGifModel(
                    url = "url",
                    title = "title",
                    link = "link",
                    rating = "G - General Audiences - Level 1"
                )
            )
        )
    }
}