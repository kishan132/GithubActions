package me.kishankumar.githubactions

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initialScreen_showsZeroOnDisplay() {
        composeTestRule.setContent {
            CalculatorApp()
        }

        composeTestRule.onNodeWithTag("calculator_display")
            .assertIsDisplayed()
            .assertTextEquals("0")
    }

    @Test
    fun performAddition_updatesDisplayWithResult() {
        composeTestRule.setContent {
            CalculatorApp()
        }

        composeTestRule.onNodeWithText("7").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("=").performClick()

        composeTestRule.onNodeWithTag("calculator_display")
            .assertTextEquals("10")
    }

    @Test
    fun performClear_resetsDisplayToZero() {
        composeTestRule.setContent {
            CalculatorApp()
        }

        composeTestRule.onNodeWithText("9").performClick()
        composeTestRule.onNodeWithText("C").performClick()

        composeTestRule.onNodeWithTag("calculator_display")
            .assertTextEquals("0")
    }
}
