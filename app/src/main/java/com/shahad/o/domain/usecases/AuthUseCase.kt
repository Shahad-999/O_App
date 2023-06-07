package com.shahad.o.domain.usecases

import com.google.firebase.auth.AuthCredential
import com.shahad.o.domain.Repository
import com.shahad.o.util.SignInResult
import kotlinx.coroutines.flow.Flow

class AuthUseCase(
    private val repository: Repository
): BaseUseCase() {

//    fun login(email: String, password: String): Flow<AuthResult> {
//        return repository.login(email, password)
//    }
//
//    fun register(email: String, password: String): Flow<AuthResult> {
//        return repository.register(email,password)
//    }


    fun googleSignIn(credential: AuthCredential): Flow<SignInResult> {
        return repository.googleSignIn(credential)
    }
}