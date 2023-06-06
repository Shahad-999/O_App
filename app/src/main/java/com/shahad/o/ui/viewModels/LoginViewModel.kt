package com.shahad.o.ui.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.shahad.o.domain.usecases.AuthUseCase
import com.shahad.o.domain.usecases.TokenUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val tokenUseCase: TokenUseCase,
    private  val authUseCase: AuthUseCase
) : ViewModel() {
    private val _googleState = mutableStateOf<AuthResult?>(null)
    val googleState: State<AuthResult?> =_googleState


    fun googleSignIn(credential: AuthCredential) {
        viewModelScope.launch {
            authUseCase.googleSignIn(credential).collect {
                Log.i("O_APP_viewModel",it.toString())
                _googleState.value = it
            }
        }

    }
    fun onClickSignup(){
        Log.i("O_APP","SIGNUP")
    }

}