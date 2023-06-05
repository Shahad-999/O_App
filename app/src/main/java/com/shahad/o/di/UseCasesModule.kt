package com.shahad.o.di

import com.shahad.o.domain.usecases.TokenUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { TokenUseCase(get()) }
}