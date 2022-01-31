package dev.carlesav.dnivalidator.feature_dni.presentation.generate_dni

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dev.carlesav.dnivalidator.R
import dev.carlesav.dnivalidator.core.TestTags
import dev.carlesav.dnivalidator.di.AppModule
import dev.carlesav.dnivalidator.presentation.BottomNavScreen
import dev.carlesav.dnivalidator.presentation.MainActivity
import dev.carlesav.dnivalidator.presentation.ui.theme.DNIValidatorTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class GenerateDniScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext


    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            DNIValidatorTheme {
                NavHost(
                    navController,
                    startDestination = BottomNavScreen.GenerateDni.screen_route
                ) {
                    composable(BottomNavScreen.GenerateDni.screen_route) {
                        GenerateDniScreen()
                    }
                }
            }
        }
    }

    @Test
    fun generateDniSuccess() {
        composeRule.onNodeWithTag(TestTags.TEXT_INPUT_GENERATE).performTextInput("12345678")
        composeRule.onNodeWithTag(TestTags.BTN_GENERATE).performClick()
        composeRule.onNodeWithTag(TestTags.GENERATION_RESULT).assertTextEquals("12345678Z")
    }

    @Test
    fun generateDniError() {
        composeRule.onNodeWithTag(TestTags.TEXT_INPUT_GENERATE).performTextInput("xxx")
        composeRule.onNodeWithTag(TestTags.BTN_GENERATE).performClick()
        composeRule.onNodeWithTag(TestTags.GENERATION_RESULT).assertTextEquals(
            context.resources.getString(R.string.invalid_document)
        )
    }
}