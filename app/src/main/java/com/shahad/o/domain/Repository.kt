package com.shahad.o.domain

import com.google.firebase.auth.AuthCredential
import com.shahad.o.domain.models.DayResult
import com.shahad.o.domain.models.Statistics
import com.shahad.o.util.Record
import com.shahad.o.util.Results
import com.shahad.o.util.SignInResult
import com.shahad.o.util.UserData
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun googleSignIn(credential: AuthCredential): Flow<SignInResult>
    fun signOut()
    fun getUser(): UserData?
    suspend fun getRecords(): List<Record>
    suspend fun sentResult(results: Results)
    suspend fun updateMode(isDark: Boolean)
    fun isDarkMode(): Flow<Boolean>

    suspend fun storeNotificationsStatus(isTurnON: Boolean)

    fun changeNotificationsStatus(isTurnON: Boolean)

    fun isNotificationsTurnIn(): Flow<Boolean>
    fun updateQuestions(questions: List<Record>)
    suspend fun getCalendarData(startDate: Long, endDate: Long): List<DayResult>?
    suspend fun getStatistics(dateRange: ClosedRange<Long>): List<Statistics>?

    fun getTodayStatus(): Flow<Boolean>
    suspend fun checkTodayRecordStatus()
}