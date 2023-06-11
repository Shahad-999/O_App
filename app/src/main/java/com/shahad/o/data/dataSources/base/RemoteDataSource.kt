package com.shahad.o.data.dataSources.base

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.shahad.o.util.Record
import com.shahad.o.util.UserData


interface RemoteDataSource {
    suspend fun signIn(credential: AuthCredential): AuthResult
    fun signOut()
    fun getUser(): UserData?
    suspend fun getRecords(): List<Record>
}