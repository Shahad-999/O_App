package com.shahad.o.di

import com.shahad.o.ui.viewModels.LoginViewModel
import com.shahad.o.ui.viewModels.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(),get()) }
}