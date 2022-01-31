package dev.carlesav.dnivalidator.feature_dni.domain.use_case

data class DniUseCases(
    val generateDni: GenerateDniUseCase,
    val validateDni: ValidateDniUseCase
)