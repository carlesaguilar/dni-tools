package dev.carlesav.dnivalidator.core

import android.util.Log

object Utils {
    fun log(msg: String) {
        Log.d("DNI", msg)
    }

    fun validateRegex(input: String, pattern: String): Boolean {
        val regex = Regex(
            pattern = pattern,
            options = setOf(RegexOption.IGNORE_CASE)
        )
        return regex.matches(input)
    }
}