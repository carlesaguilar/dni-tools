package dev.carlesav.dnivalidator.feature_dni.presentation.generate_dni

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.carlesav.dnivalidator.feature_dni.domain.use_case.DniUseCases
import dev.carlesav.dnivalidator.presentation.DniResultState
import javax.inject.Inject

@HiltViewModel
class GenerateDniViewModel @Inject constructor(
    private val dniUseCases: DniUseCases
) : ViewModel() {
    private val _dniResultState = mutableStateOf(DniResultState(isGenerated = false))
    val dniResultState: State<DniResultState> = _dniResultState

    fun generateDni(dni: String) {
        val result = dniUseCases.generateDni(dni)
        when (result.isValid) {
            true -> {
                _dniResultState.value = dniResultState.value.copy(
                    isGenerated = true,
                    isValid = true,
                    message = result.message
                )
            }
            false -> {
                _dniResultState.value = dniResultState.value.copy(
                    isGenerated = true,
                    isValid = false
                )
            }
        }
    }

    fun resetDniResult() {
        _dniResultState.value = dniResultState.value.copy(
            isGenerated = false
        )
    }
}