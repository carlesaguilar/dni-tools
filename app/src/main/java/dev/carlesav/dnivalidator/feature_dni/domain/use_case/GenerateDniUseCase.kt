package dev.carlesav.dnivalidator.feature_dni.domain.use_case

import dev.carlesav.dnivalidator.feature_dni.domain.exception.InvalidDocumentException
import dev.carlesav.dnivalidator.feature_dni.domain.utils.DniUtils
import dev.carlesav.dnivalidator.feature_dni.presentation.components.DniResult

class GenerateDniUseCase {
    operator fun invoke(dni: String): DniResult {
        try {
            val generatedChar = DniUtils.generateDniLetter(dni)
            return DniResult(
                isValid = true,
                message = dni + generatedChar
            )
        } catch (ex: InvalidDocumentException) {
            return DniResult(isValid = false)
        } catch (ex: Exception) {
            return DniResult(isValid = false)
        }
    }
}