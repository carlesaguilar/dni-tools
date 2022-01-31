package dev.carlesav.dnivalidator.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.carlesav.dnivalidator.feature_dni.presentation.generate_dni.GenerateDniScreen
import dev.carlesav.dnivalidator.feature_dni.presentation.validate_dni.ValidateDniScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavScreen.ValidateDni.screen_route) {
        composable(BottomNavScreen.ValidateDni.screen_route) {
            ValidateDniScreen()
        }
        composable(BottomNavScreen.GenerateDni.screen_route) {
            GenerateDniScreen()
        }
    }
}