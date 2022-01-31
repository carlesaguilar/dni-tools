package dev.carlesav.dnivalidator.feature_dni.domain.utils

val integerChars = '0'..'9'

fun String.isOnlyNumbers(): Boolean {
    var dotOccurred = 0
    return this.all { it in integerChars || it == '.' && dotOccurred++ < 1 }
}