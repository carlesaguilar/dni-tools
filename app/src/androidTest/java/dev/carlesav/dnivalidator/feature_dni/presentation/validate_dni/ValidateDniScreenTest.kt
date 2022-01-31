package dev.carlesav.dnivalidator.feature_dni.presentation.validate_dni

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
class ValidateDniScreenTest {
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
                    startDestination = BottomNavScreen.ValidateDni.screen_route
                ) {
                    composable(BottomNavScreen.ValidateDni.screen_route) {
                        ValidateDniScreen()
                    }
                }
            }
        }
    }

    @Test
    fun checkValidDni() {
        composeRule.onNodeWithTag(TestTags.TEXT_INPUT_VALIDATE).performTextInput("12345678z")
        composeRule.onNodeWithTag(TestTags.BTN_VALIDATE).performClick()
        composeRule.onNodeWithTag(TestTags.VALIDATION_RESULT)
            .assertTextEquals(context.resources.getString(R.string.valid_document))
    }

    @Test
    fun checkInvalidDni() {
        composeRule.onNodeWithTag(TestTags.TEXT_INPUT_VALIDATE).performTextInput("12345679z")
        composeRule.onNodeWithTag(TestTags.BTN_VALIDATE).performClick()
        composeRule.onNodeWithTag(TestTags.VALIDATION_RESULT)
            .assertTextEquals(context.resources.getString(R.string.invalid_document))
    }
}