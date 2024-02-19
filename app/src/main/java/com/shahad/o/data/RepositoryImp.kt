package com.shahad.o.data

import com.google.firebase.auth.AuthCredential
import com.shahad.o.data.dataSources.base.DataStoreDataSource
import com.shahad.o.data.dataSources.base.RemoteDataSource
import com.shahad.o.data.dataSources.mappers.toModel
import com.shahad.o.data.dataSources.mappers.toStatistics
import com.shahad.o.domain.Repository
import com.shahad.o.domain.models.DayResult
import com.shahad.o.domain.models.Statistics
import com.shahad.o.util.Record
import com.shahad.o.util.Results
import com.shahad.o.util.SignInResult
import com.shahad.o.util.UserData
import com.shahad.o.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.internal.wait

class RepositoryImp(
    private val datastoreDataSource: DataStoreDataSource,
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override fun signOut() {
        remoteDataSource.signOut()
    }

    override fun googleSignIn(credential: AuthCredential): Flow<SignInResult> {
        return flow {
            val user = remoteDataSource.signIn(credential).user
            user?.let{ createUserOwnQuestion(it.uid) }
            emit(
                SignInResult(
                    data = user?.let {
                        UserData(
                            userId = it.uid,
                            userName = it.displayName,
                            profilePictureUrl = it.photoUrl?.toString(),
                            email = it.email
                        )
                    },
                    error = null
                )
            )
        }.catch {
            emit(
                SignInResult(
                    data = null,
                    error = it.message.toString()
                )
            )
        }
    }


    private suspend fun createUserOwnQuestion(uid: String){
        remoteDataSource.createUserOwnQuestion(uid)
    }

    override fun getUser(): UserData? {
        return remoteDataSource.getUser()
    }

    override suspend fun getRecords(): List<Record> {
        return remoteDataSource.getRecords()
    }

    override suspend fun sentResult(results: Results) {
        remoteDataSource.sentResult(results)
        datastoreDataSource.updateTodayStatus(true)
    }

    override suspend fun checkTodayRecordStatus(){
        val status = remoteDataSource.getIfThereIsRecordToday()
        status.log(tag = "O_APP_STATUS IS ")
        datastoreDataSource.updateTodayStatus(status)
    }
    override suspend fun updateMode(isDark: Boolean) {
        datastoreDataSource.updateMode(isDark)
    }

    override fun isDarkMode(): Flow<Boolean> {
        return datastoreDataSource.isDarkMode()
    }

    override fun changeNotificationsStatus(isTurnON: Boolean) {
        if(isTurnON) remoteDataSource.subscribeToNotifications() else remoteDataSource.unsubscribeToNotifications()
    }

    override suspend fun storeNotificationsStatus(isTurnON: Boolean) {
        datastoreDataSource.updateNotificationStatus(isTurnON)
    }

    override fun isNotificationsTurnIn(): Flow<Boolean> {
        return datastoreDataSource.isNotificationsTurnOn()
    }

    override fun updateQuestions(questions: List<Record>) {
        remoteDataSource.updateQuestions(questions)
    }

    override suspend fun getCalendarData(startDate: Long, endDate: Long): List<DayResult>? {
        return try {
            remoteDataSource.getCalendarData(startDate, endDate).toModel()
        }catch (e: Exception){
            null
        }
    }

    override suspend fun getStatistics(dateRange: ClosedRange<Long>): List<Statistics>? {
        return try {
            remoteDataSource.getStatistics(dateRange.start, dateRange.endInclusive).toStatistics()
        }catch (e: Exception){
            null
        }
    }

    override fun getTodayStatus(): Flow<Boolean> {
        return datastoreDataSource.isTodayDone()
    }

}