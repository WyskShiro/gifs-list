package will.shiro.giphy.gifs.search

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
import will.shiro.giphy.domain.repositories.IGifRepository
import will.shiro.giphy.domain.usecases.GetSearchGifUseCase
import will.shiro.giphy.gifs.home.models.UIGifModel
import will.shiro.giphy.gifs.search.models.UIGifSearch

@ExperimentalCoroutinesApi
class GifSearchViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var getSearchGifUseCase: GetSearchGifUseCase

    @MockK
    private lateinit var gifRepository: IGifRepository

    @MockK
    private lateinit var resources: Resources
    private lateinit var viewModel: GifSearchViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel =
            GifSearchViewModel(
                getSearchGifUseCase,
                gifRepository,
                resources
            )
    }

    @Test
    fun testGetSearchGifsSuccess() = runTest {
        coEvery { getSearchGifUseCase("asdf") } returns listOf(
            Gif(
                url = "url",
                title = "title",
                link = "link",
                rating = "r"
            )
        )
        every { resources.getString(R.string.r_restricted_rating) } returns "R - Restricted - Level 4"
        viewModel.getSearchGifs("asdf")
        advanceUntilIdle()

        assertEquals(
            viewModel.state.value,
            UIGifSearch.State(
                searchText = "asdf",
                searchGifs = listOf(
                    UIGifModel(
                        url = "url",
                        title = "title",
                        link = "link",
                        rating = "R - Restricted - Level 4"
                    )
                )
            )
        )
    }
}