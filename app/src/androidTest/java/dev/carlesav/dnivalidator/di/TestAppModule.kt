package dev.carlesav.dnivalidator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.carlesav.dnivalidator.feature_dni.domain.use_case.DniUseCases
import dev.carlesav.dnivalidator.feature_dni.domain.use_case.GenerateDniUseCase
import dev.carlesav.dnivalidator.feature_dni.domain.use_case.ValidateDniUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideTaskUseCases(): DniUseCases {
        return DniUseCases(
            validateDni = ValidateDniUseCase(),
            generateDni = GenerateDniUseCase()
        )
    }
}