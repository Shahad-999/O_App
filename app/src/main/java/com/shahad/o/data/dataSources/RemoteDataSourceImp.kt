package com.shahad.o.data.dataSources

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shahad.o.data.dataSources.base.RemoteDataSource
import com.shahad.o.util.Record
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
        val user =  firebaseAuth.currentUser?.run {
            UserData(
                userId = uid,
                userName = displayName,
                profilePictureUrl = photoUrl?.toString()
            )
        }
        return user
    }

    override suspend fun getRecords(): List<Record> {
        val query = firestore.collection("current_recodes")
            .whereEqualTo("uid",getUser()?.userId)
            .get()
            .await()
        val records = query.documents.firstOrNull()?.data?.toRecords()
        records.log()
        return records ?: emptyList()
    }

    fun Map<String,Any>.toRecords(): List<Record> {
        return (this["recordes"] as List<Map<String,String>>).mapIndexed { index, map ->
            Record(order = index, question = map["question"] ?: "", imageUrl = map["imageUrl"] ?: "")
        }
    }
    companion object{
        const val TAG = "REMOTE_TAG"
    }

}