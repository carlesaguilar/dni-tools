package dev.carlesav.dnivalidator.feature_dni.domain.use_case

import dev.carlesav.dnivalidator.feature_dni.domain.exception.InvalidDocumentException
import dev.carlesav.dnivalidator.feature_dni.domain.utils.DniUtils

class ValidateDniUseCase {
    operator fun invoke(dniToValidate: String): Boolean {
        try {
            return DniUtils.isValid(dniToValidate)
        } catch (ex: InvalidDocumentException) {
            return false
        } catch (ex: Exception) {
            return false
        }
    }
}