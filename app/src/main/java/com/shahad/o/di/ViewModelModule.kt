package com.shahad.o.di

import com.shahad.o.ui.viewModels.HomeViewModel
import com.shahad.o.ui.viewModels.LoginViewModel
import com.shahad.o.ui.viewModels.MainViewModel
import com.shahad.o.ui.viewModels.RecordsViewModel
import com.shahad.o.ui.viewModels.SettingViewModel
import com.shahad.o.ui.viewModels.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(),get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { RecordsViewModel(get()) }
    viewModel { SettingViewModel(get(),get(),get()) }
}