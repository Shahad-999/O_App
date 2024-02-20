package com.shahad.o.data

import com.google.firebase.auth.AuthCredential
import com.shahad.o.domain.Repository
import com.shahad.o.domain.models.DayResult
import com.shahad.o.domain.models.Result
import com.shahad.o.domain.models.Statistics
import com.shahad.o.util.Record
import com.shahad.o.util.Results
import com.shahad.o.util.SignInResult
import com.shahad.o.util.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepositoryImp : Repository {
    var userData: UserData? = UserData(
        userId = "1111",
        userName = "SHAHAD",
        email = "shahad.h.999.a@gmail.com",
        profilePictureUrl = null
    )
    var records = listOf(
        Record(
            order = 0,
            question = "Do you learn anything today ?",
            imageUrl = "https://i.ibb.co/xgzRpcb/yellow-rubber-duck-for-bath.png",
            positiveAnswer = true,
            weight = 1
        ),

        Record(
            order = 1,
            question = "Did you say love you to yourself ?",
            imageUrl = "https://i.ibb.co/qg0ZffP/yellow-rubber-duck-for-bath-1.png",
            positiveAnswer = true,
            weight = 1
        ),

        Record(
            order = 2,
            question = "Do you expert love for your family",
            imageUrl = "https://i.ibb.co/Hqfwdsq/yellow-rubber-duck-for-bath-2.png",
            positiveAnswer = true,
            weight = 1
        ),

        Record(
            order = 3,
            question = "Do you pray and thank god for this day ?",
            imageUrl = "https://i.ibb.co/sFH1nvc/yellow-rubber-duck-for-bath-3.png",
            positiveAnswer = true,
            weight = 1
        ),
        Record(
            order = 4,
            question = "Do you read anything from Quran ?",
            imageUrl = "https://i.ibb.co/gWSrdwL/yellow-rubber-duck-for-bath-4.png",
            positiveAnswer = true,
            weight = 1
        ),
        Record(
            order = 5,
            question = "Are you HAPPY? :) ",
            imageUrl = "https://i.ibb.co/pRD3RLr/yellow-rubber-duck-for-bath-5.png",
            positiveAnswer = true,
            weight = 1
        )

    )
    var dayResults = mutableListOf<DayResult>()
    override fun googleSignIn(credential: AuthCredential): Flow<SignInResult> {
        return flow {
            emit(SignInResult(
                data = userData,
                error = null
            ))
        }
    }
    var isDarkMood = false
    var isNotificationsOn = false

    override fun signOut() {
        userData = null
    }

    override fun getUser(): UserData? {
        return userData
    }

    override suspend fun getRecords(): List<Record> {
        return records
    }

    override suspend fun sentResult(results: Results) {
        dayResults.add(DayResult(results.date, percent = results.percent.toDouble(), results = results.records.map { Result(it.isPositive,it.question,it.weight.toInt(),it.answer) }))
    }

    override suspend fun updateMode(isDark: Boolean) {
        isDarkMood = isDark
    }

    override fun isDarkMode(): Flow<Boolean> {
        return flow {
            this.emit(isDarkMood)
        }
    }

    override suspend fun storeNotificationsStatus(isTurnON: Boolean) {
        isNotificationsOn = isTurnON
    }

    override fun changeNotificationsStatus(isTurnON: Boolean) {
        isNotificationsOn = isTurnON
    }

    override fun isNotificationsTurnIn(): Flow<Boolean> {
        return flow { emit(isNotificationsOn) }
    }

    override fun updateQuestions(questions: List<Record>) {
        records = questions
    }

    override suspend fun getCalendarData(startDate: Long, endDate: Long): List<DayResult>? {
        return dayResults.filter { it.date in startDate..endDate }
    }

    override suspend fun getStatistics(dateRange: ClosedRange<Long>): List<Statistics>? {
        return listOf()
    }

    override fun getTodayStatus(): Flow<Boolean> {
        return flow { }
    }

    override suspend fun checkTodayRecordStatus() {

    }
}