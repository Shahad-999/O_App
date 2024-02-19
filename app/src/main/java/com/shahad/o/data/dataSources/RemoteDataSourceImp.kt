package com.shahad.o.data.dataSources

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.shahad.o.data.dataSources.base.RemoteDataSource
import com.shahad.o.data.dataSources.mappers.toFirebaseDto
import com.shahad.o.data.dataSources.mappers.toRecords
import com.shahad.o.data.dataSources.models.DayResultDto
import com.shahad.o.util.Record
import com.shahad.o.util.Results
import com.shahad.o.util.UserData
import com.shahad.o.util.log
import com.shahad.o.util.toDataClass
import kotlinx.coroutines.tasks.await

class RemoteDataSourceImp(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val firebaseMessaging: FirebaseMessaging
) : RemoteDataSource {
    override suspend fun signIn(credential: AuthCredential): AuthResult {
        return firebaseAuth.signInWithCredential(credential).await()
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override fun getUser(): UserData? {
        val user = firebaseAuth.currentUser?.run {
            UserData(
                userId = uid,
                userName = displayName,
                profilePictureUrl = photoUrl?.toString(),
                email = email
            )
        }
        return user
    }

    override suspend fun getRecords(): List<Record> {
//        getCalendarData(Instant.parse("2023-10-09T22:19:44.475Z"),Instant.parse("2023-12-02T22:19:44.475Z"),).log()
        getUser()?.userId?.let {
            val query = fireStore.collection("questions")
                .document(it)
                .get()
                .await()
            val records = query.data?.toRecords()
            records.log()
            return records ?: emptyList()
        }
        return emptyList()
    }


    override fun sentResult(results: Results) {
        getUser()?.let {
            fireStore.collection("users_records").document(it.userId)
                .collection("Calendar")
                .document(results.date.toString())
                .set(results.toFirebaseDto())
        }
    }

    override suspend fun createUserOwnQuestion(uid: String) {
        val query = fireStore.collection("questions")
            .document(uid)
            .get()
            .await()
        if(query.data==null){
            sentQuestions(getDefaultRecords(), uid)
        }
    }

    private suspend fun getDefaultRecords(): List<Record> {
        val query = fireStore.collection("questions").document("default_questions")
            .get()
            .await()
        val records = query.data?.toRecords()
        records.log()
        return records ?: emptyList()
    }

    override fun updateQuestions(questions: List<Record>) {
        getUser()?.userId?.let {
            sentQuestions(questions, it)
        }
    }

    override suspend fun getCalendarData(startDate: Long, endDate: Long): List<DayResultDto> {
        getUser()?.let {
            val ll = fireStore.collection("users_records").document(it.userId)
                .collection("Calendar")
                .whereGreaterThanOrEqualTo("date", startDate)
                .whereLessThanOrEqualTo("date", endDate)
                .get().await()
            return ll.documents.mapNotNull { date ->
                date?.data?.toDataClass()
            }
        }
        return emptyList()

    }

    override suspend fun getStatistics(startDate: Long, endDate: Long): Map<Long, Double> {

        getUser()?.let {
            val ll = fireStore.collection("users_records").document(it.userId)
                .collection("Calendar")
                .whereGreaterThanOrEqualTo("date", startDate)
                .whereLessThanOrEqualTo("date", endDate)

                .get().await()
            return ll.documents.mapNotNull { date ->
                date.data?.let { document -> (document["date"] as Long) to (document["percent"] as Double) }
            }.toMap()
        }
        return emptyMap()
    }

    private fun sentQuestions(questions: List<Record>, uid: String) {
        fireStore.collection("questions")
            .document(uid)
            .set(questions.toFirebaseDto())
    }

    override fun subscribeToNotifications() {
        firebaseMessaging.subscribeToTopic("all")
    }

    override fun unsubscribeToNotifications() {
        firebaseMessaging.unsubscribeFromTopic("all")
    }


}