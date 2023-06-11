package com.shahad.o.domain

import com.google.firebase.auth.AuthCredential
import com.shahad.o.util.Record
import com.shahad.o.util.SignInResult
import com.shahad.o.util.UserData
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun googleSignIn(credential: AuthCredential): Flow<SignInResult>
    fun signOut()
    fun getUser(): UserData?
    suspend fun getRecords(): List<Record>
}