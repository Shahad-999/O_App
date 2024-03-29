package com.shahad.o.di

import com.shahad.o.ui.viewModels.CalendarViewModel
import com.shahad.o.ui.viewModels.HomeViewModel
import com.shahad.o.ui.viewModels.LoginViewModel
import com.shahad.o.ui.viewModels.MainViewModel
import com.shahad.o.ui.viewModels.QuestionsViewModel
import com.shahad.o.ui.viewModels.RecordsViewModel
import com.shahad.o.ui.viewModels.SettingViewModel
import com.shahad.o.ui.viewModels.StatisticsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(),get(),get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get(),get(),get()) }
    viewModel { RecordsViewModel(get(),get()) }
    viewModel { SettingViewModel(get(),get(),get(),get()) }
    viewModel { QuestionsViewModel(get(),get()) }
    viewModel { CalendarViewModel(get()) }
    viewModel { StatisticsViewModel(get()) }
}