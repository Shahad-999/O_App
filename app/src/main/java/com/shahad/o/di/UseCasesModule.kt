package com.shahad.o.di

import com.shahad.o.domain.usecases.AuthUseCase
import com.shahad.o.domain.usecases.TokenUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { TokenUseCase(get()) }
    single { AuthUseCase(get()) }
}