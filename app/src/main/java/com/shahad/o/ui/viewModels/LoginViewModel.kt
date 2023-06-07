package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.shahad.o.domain.usecases.AuthUseCase
import com.shahad.o.ui.states.SignInState
import com.shahad.o.util.SignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private  val authUseCase: AuthUseCase,
) : ViewModel() {
    private val _signInState = MutableStateFlow(SignInState())
    val signInState: MutableStateFlow<SignInState> =_signInState


    fun googleSignIn(credential: AuthCredential) {
        viewModelScope.launch {
            authUseCase.googleSignIn(credential).collect {
                onSignInResult(it)
            }
        }

    }

    private fun onSignInResult(result: SignInResult){
        _signInState.value =
            SignInState(
                isSuccess = result.data  != null,
                error = result.error
            )

    }


}