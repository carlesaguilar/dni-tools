package dev.carlesav.dnivalidator.presentation

import dev.carlesav.dnivalidator.R

sealed class BottomNavScreen(var title: String, var icon: Int, var screen_route: String) {
    object ValidateDni : BottomNavScreen("Validate", R.drawable.ic_check, "validate")
    object GenerateDni : BottomNavScreen("Generate", R.drawable.ic_config, "generate")
}