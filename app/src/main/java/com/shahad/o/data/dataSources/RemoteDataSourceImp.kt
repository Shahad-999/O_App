package com.shahad.o.data.dataSources

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.shahad.o.data.dataSources.base.RemoteDataSource
import com.shahad.o.util.Record
import com.shahad.o.util.RecordResult
import com.shahad.o.util.UserData
import com.shahad.o.util.log
import kotlinx.coroutines.tasks.await

class RemoteDataSourceImp(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
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
        val query = firestore.collection("questions").document("default_questions")
            .get()
            .await()
        val records = query.data?.toRecords()
        records.log()
        return records ?: emptyList()
    }


    override fun sentResult(results: List<RecordResult>) {
        getUser()?.let {
            firestore.collection("users_records").document(it.userId)
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

    private fun List<RecordResult>.toFirebaseResults(): HashMap<String, Any> {
        val results = this.map {
            return@map hashMapOf<String, Any>(
                "isPositive" to it.isPositive,
                "question" to it.question,
                "weight" to it.weight
            )
        }
        return hashMapOf(
            "results" to results,
            "date" to System.currentTimeMillis()
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

    companion object {
        const val TAG = "REMOTE_TAG"
    }

}