package com.shahad.o.domain

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getToken(): String?


    fun signUp(): String
    fun login(email: String,password: String): Flow<AuthResult>
    fun register(email: String,password: String): Flow<AuthResult>
    fun googleSignIn(credential: AuthCredential): Flow<AuthResult>
}