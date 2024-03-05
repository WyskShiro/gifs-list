package will.shiro.giphy.gifs.search.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import will.shiro.giphy.gifs.components.GifComposable
import will.shiro.giphy.gifs.home.models.UIGifModel
import will.shiro.giphy.theme.GifsListTheme

class GifComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testGifComposable() {
        val gifModel = UIGifModel(
            "url",
            "title",
            "link",
            "rating"
        )
        var clickedGif: UIGifModel? = null
        val onGifClick: (UIGifModel) -> Unit = { gif -> clickedGif = gif }

        composeTestRule.setContent {
            GifsListTheme {
                GifComposable(
                    gifModel,
                    onGifClick
                )
            }
        }

        composeTestRule.onNodeWithTag("GifComposableTest_${gifModel.url}")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("GifComposableTest_${gifModel.url}")
            .performClick()

        assertEquals(
            clickedGif,
            gifModel
        )
    }
}