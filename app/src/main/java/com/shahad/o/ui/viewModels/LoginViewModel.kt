package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.TokenUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val tokenUseCase: TokenUseCase
) : ViewModel() {


    fun saveToken(token: String) {
        viewModelScope.launch {
            tokenUseCase.saveToken(token)
        }
    }
}