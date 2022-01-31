package dev.carlesav.dnivalidator.presentation

data class DniResultState(
    val isGenerated: Boolean = false,
    val isValid: Boolean = false,
    val message: String = ""
)