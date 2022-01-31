package dev.carlesav.dnivalidator.feature_dni.domain.utils

import com.google.common.truth.Truth.assertThat
import dev.carlesav.dnivalidator.feature_dni.domain.exception.InvalidDocumentException
import org.junit.Test

class DniUtilsTest {
    @Test
    fun validateDni() {
        var isValidDni = false

        isValidDni = DniUtils.isValid("12345678Z")
        assertThat(isValidDni).isEqualTo(true)

        isValidDni = DniUtils.isValid("12345678z")
        assertThat(isValidDni).isEqualTo(true)

        try {
            DniUtils.isValid("123456zzz")
        } catch (ex: InvalidDocumentException) {
            assert(true)
        }

        try {
            DniUtils.isValid("zzz")
        } catch (ex: InvalidDocumentException) {
            assert(true)
        }

        try {
            DniUtils.isValid("12345")
        } catch (ex: InvalidDocumentException) {
            assert(true)
        }
    }

    @Test
    fun generateDni() {
        val generatedChar = DniUtils.generateDniLetter("12345678")
        assertThat(generatedChar).isEqualTo("Z")
    }
}