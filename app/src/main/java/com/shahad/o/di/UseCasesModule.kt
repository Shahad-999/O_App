package com.shahad.o.di

import com.shahad.o.domain.usecases.AuthUseCase
import com.shahad.o.domain.usecases.CheckTodayStatusUseCase
import com.shahad.o.domain.usecases.GetCalendar
import com.shahad.o.domain.usecases.GetStatisticUseCase
import com.shahad.o.domain.usecases.GetTodayStatus
import com.shahad.o.domain.usecases.NotificationsUseCase
import com.shahad.o.domain.usecases.RecordsUseCase
import com.shahad.o.domain.usecases.ResultsUseCase
import com.shahad.o.domain.usecases.SignOutUseCase
import com.shahad.o.domain.usecases.ThemeUseCase
import com.shahad.o.domain.usecases.UpdateQuestionsUseCase
import com.shahad.o.domain.usecases.UserInfoUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { AuthUseCase(get()) }
    single { UserInfoUseCase(get()) }
    single { RecordsUseCase(get()) }
    single { ResultsUseCase(get()) }
    single { ThemeUseCase(get()) }
    single { NotificationsUseCase(get()) }
    single { UpdateQuestionsUseCase(get()) }
    single { SignOutUseCase(get()) }
    single { GetCalendar(get()) }
    single { GetStatisticUseCase(get()) }
    single { GetTodayStatus(get()) }
    single { CheckTodayStatusUseCase(get()) }
}