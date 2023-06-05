package com.shahad.o.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.TokenUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val tokenUseCase: TokenUseCase
) : ViewModel() {


    fun onClickLogin(){
        Log.i("O_APP","LOGIN")
    }

    fun onClickSignup(){
        Log.i("O_APP","SIGNUP")
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            tokenUseCase.saveToken(token)
        }
    }
}