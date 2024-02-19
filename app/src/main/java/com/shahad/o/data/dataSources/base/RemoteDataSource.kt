package com.shahad.o.data.dataSources.base

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.shahad.o.data.dataSources.models.DayResultDto
import com.shahad.o.util.Record
import com.shahad.o.util.Results
import com.shahad.o.util.UserData


interface RemoteDataSource {
    suspend fun signIn(credential: AuthCredential): AuthResult
    fun signOut()
    fun getUser(): UserData?
    suspend fun getRecords(): List<Record>
    suspend fun sentResult(results: Results)

    suspend fun createUserOwnQuestion(uid: String)
    fun updateQuestions(questions: List<Record>)

    suspend fun getCalendarData(startDate: Long,endDate: Long): List<DayResultDto>
    suspend fun getStatistics(startDate: Long,endDate: Long): Map<Long,Double>
     fun subscribeToNotifications()
     fun unsubscribeToNotifications()
    suspend fun getIfThereIsRecordToday(): Boolean
}