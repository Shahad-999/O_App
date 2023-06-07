package com.shahad.o.data.dataSources

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.shahad.o.data.dataSources.base.RemoteDataSource
import com.shahad.o.util.UserData
import kotlinx.coroutines.tasks.await

class RemoteDataSourceImp(
    private val firebaseAuth: FirebaseAuth
) : RemoteDataSource {
    override suspend fun signIn(credential: AuthCredential): AuthResult {
        return firebaseAuth.signInWithCredential(credential).await()
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override fun getUser(): UserData? {
        return firebaseAuth.currentUser?.run {
            UserData(
                userId = uid,
                userName = displayName,
                profilePictureUrl = photoUrl?.toString()
            )
        }
    }

}