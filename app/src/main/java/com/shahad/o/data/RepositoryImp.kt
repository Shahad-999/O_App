package com.shahad.o.data

import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.shahad.o.data.dataSources.base.DataStoreDataSource
import com.shahad.o.data.dataSources.base.RemoteDataSource
import com.shahad.o.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RepositoryImp(
    val datastoreDataSource: DataStoreDataSource,
    val remoteDataSource: RemoteDataSource
): Repository {
    val firebaseAuth = FirebaseAuth.getInstance()
    override suspend fun getToken(): String? {
        return datastoreDataSource.getToken()
    }

     suspend fun deleteToken() {
        return datastoreDataSource.deleteToken()
    }

    private suspend fun saveToken(token: String) {
        return datastoreDataSource.saveToken(token)
    }

    override fun signUp(): String {
        return  remoteDataSource.signUp()
    }

    override fun login(email: String, password: String): Flow<AuthResult> {
        return flow{
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(result)
        }.catch {
            Log.i("O_APP","NO LOGIN")
        }
    }

    override fun register(email: String, password: String): Flow<AuthResult> {
        return flow{
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(result)
        }.catch {
            Log.i("O_APP","NO REGISTER")
        }
    }

    override fun googleSignIn(credential: AuthCredential): Flow<AuthResult> {
        return flow{
            println("hi google imp ")
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(result)
        }.catch {
            Log.i("O_APP","NO GOOGLE")
        }
    }


}