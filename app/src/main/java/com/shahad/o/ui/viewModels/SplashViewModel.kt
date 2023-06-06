package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.TokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val tokenUseCase: TokenUseCase
): ViewModel() {

    val token = MutableStateFlow<String?>("")


    init {
        viewModelScope.launch {
            tokenUseCase.getToken().let {
                token.value = it
            }
        }
    }

}