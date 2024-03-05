package will.shiro.giphy.gifs.home.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class GifHomeSearchTextComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testGifHomeSearchTextComposable() {
        var click = false
        composeTestRule.setContent {
            GifHomeSearchTextComposable {
                click = true
            }
        }
        composeTestRule.onNodeWithText("Search").assertExists()
        composeTestRule.onNodeWithText("Search").performClick()
        assertTrue(click)
    }
}
