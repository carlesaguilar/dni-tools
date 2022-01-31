package dev.carlesav.dnivalidator.feature_dni.presentation.components

data class DniResult(
    val isValid: Boolean,
    val message: String = ""
)