package dev.carlesav.dnivalidator.feature_dni.domain.utils

import dev.carlesav.dnivalidator.core.Utils
import dev.carlesav.dnivalidator.feature_dni.domain.exception.InvalidDocumentException

object DniUtils {
    fun isValid(dniToValidate: String): Boolean {
        val dniRegex = "[0-9]+[a-zA-Z]"

        if (!Utils.validateRegex(dniToValidate, dniRegex)) {
            throw InvalidDocumentException()
        }

        val dniToValidate = dniToValidate.uppercase()
        val documentWithoutLetter = dniToValidate.substring(0, dniToValidate.length - 1)
        val validChar = generateDniLetter(documentWithoutLetter)
        return validChar == dniToValidate.substring(dniToValidate.length - 1)
    }

    fun generateDniLetter(dni: String): String {
        if (!dni.isOnlyNumbers()) {
            throw InvalidDocumentException()
        }

        val chars = "TRWAGMYFPDXBNJZSQVHLCKET"
        val module = dni.toInt() % 23
        return chars[module].toString().uppercase()
    }
}