package will.shiro.giphy.gifs.search.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import will.shiro.giphy.R
import will.shiro.giphy.theme.GifsListTheme

class GifSearchTextComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun assertTextFieldIsDisplayed() {
        composeTestRule.setContent {
            GifsListTheme {
                GifSearchTextComposable(value = "Test")
            }
        }
        composeTestRule.onNodeWithText("Test").assertIsDisplayed()
    }

    @Test
    fun assertTextFieldValueChange() {
        var typeText = ""
        composeTestRule.setContent {
            GifsListTheme {
                GifSearchTextComposable(
                    value = "Test",
                    onValueChange = {
                        typeText = "typed"
                    })
            }
        }
        composeTestRule.onNodeWithText("Test").performTextInput("typed")
        assertTrue(typeText == "typed")
    }

    @Test
    fun assertBackButtonDisplayed() {
        composeTestRule.setContent {
            GifsListTheme {
                GifSearchTextComposable(
                    value = "Test",
                    onBackClick = {
                    })
            }
        }
        composeTestRule.onNodeWithTag(R.drawable.ic_arrow_back_24.toString())
            .assertIsDisplayed()
    }

    @Test
    fun assertBackButtonClick() {
        var backClick = false
        composeTestRule.setContent {
            GifsListTheme {
                GifSearchTextComposable(
                    value = "Test",
                    onBackClick = {
                        backClick = true
                    })
            }
        }
        composeTestRule.onNodeWithTag(R.drawable.ic_arrow_back_24.toString()).performClick()
        assertTrue(backClick)
    }
}