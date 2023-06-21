package com.shahad.o.domain

import com.google.firebase.auth.AuthCredential
import com.shahad.o.util.Record
import com.shahad.o.util.RecordResult
import com.shahad.o.util.SignInResult
import com.shahad.o.util.UserData
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun googleSignIn(credential: AuthCredential): Flow<SignInResult>
    fun signOut()
    fun getUser(): UserData?
    suspend fun getRecords(): List<Record>
    fun sentResult(results: List<RecordResult>)
    suspend fun updateMode(isDark: Boolean)
    fun isDarkMode(): Flow<Boolean>

    suspend fun storeNotificationsStatus(isTurnON: Boolean)

    fun changeNotificationsStatus(isTurnON: Boolean)

    fun isNotificationsTurnIn(): Flow<Boolean>

}