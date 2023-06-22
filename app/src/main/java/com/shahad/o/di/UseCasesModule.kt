package com.shahad.o.di

import com.shahad.o.domain.usecases.AuthUseCase
import com.shahad.o.domain.usecases.NotificationsUseCase
import com.shahad.o.domain.usecases.RecordsUseCase
import com.shahad.o.domain.usecases.ResultsUseCase
import com.shahad.o.domain.usecases.ThemeUseCase
import com.shahad.o.domain.usecases.UserInfoUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { AuthUseCase(get()) }
    single { UserInfoUseCase(get()) }
    single { RecordsUseCase(get()) }
    single { ResultsUseCase(get()) }
    single { ThemeUseCase(get()) }
    single { NotificationsUseCase(get()) }
}