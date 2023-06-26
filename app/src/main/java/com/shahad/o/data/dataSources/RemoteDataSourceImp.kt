package com.shahad.o.data.dataSources

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.shahad.o.data.dataSources.base.RemoteDataSource
import com.shahad.o.util.Record
import com.shahad.o.util.Results
import com.shahad.o.util.UserData
import com.shahad.o.util.log
import kotlinx.coroutines.tasks.await

class RemoteDataSourceImp(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
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

    private suspend fun getDefaultRecords(): List<Record> {
        val query = fireStore.collection("questions").document("default_questions")
            .get()
            .await()
        val records = query.data?.toRecords()
        records.log()
        return records ?: emptyList()
    }


    override fun sentResult(results: Results) {
        getUser()?.let {
            fireStore.collection("users_records").document(it.userId)
                .set(
                    hashMapOf(
                        "daily_records_results" to FieldValue.arrayUnion(
                            results.toFirebaseResults()
                        )
                    ),
                    SetOptions.merge()
                )
        }
    }

    override suspend fun createUserOwnQuestion(uid: String) {
        sentQuestions(getDefaultRecords(),uid)
    }

    override fun updateQuestions(questions: List<Record>) {
        getUser()?.userId?.let {
            sentQuestions(questions,it)
        }
    }

    private fun sentQuestions(questions: List<Record>, uid: String){
        fireStore.collection("questions")
            .document(uid)
            .set(questions.toMap())
    }

    private fun Results.toFirebaseResults(): HashMap<String, Any> {
        val results = records.map {
            return@map hashMapOf<String, Any>(
                "isPositive" to it.isPositive,
                "question" to it.question,
                "weight" to it.weight
            )
        }
        return hashMapOf(
            "results" to results,
            "date" to date,
            "percent" to percent
        )
    }

    private fun Map<String, Any>.toRecords(): List<Record> {
        return (this["questions"] as List<Map<String, Any>>).mapIndexed { index, map ->
            Record(
                order = index,
                question = (map["text"] ?: "") as String,
                imageUrl = (map["image"] ?: "") as String,
                weight = (map["weight"] ?: 1) as Long,
                positive_answer = (map["positive_answer"] ?: true) as Boolean
            )
        }
    }

    private fun List<Record>.toMap(): HashMap<String, List<HashMap<String, Any>>> {
        return hashMapOf(
            "questions" to map {
                hashMapOf(
                    "text" to it.question,
                    "image" to it.imageUrl,
                    "order" to it.order,
                    "weight" to it.weight,
                    "positive_answer" to it.positive_answer
                )
            }
        )
    }

    companion object {
        const val TAG = "REMOTE_TAG"
    }

}