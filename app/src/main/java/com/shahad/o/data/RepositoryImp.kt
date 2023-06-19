package com.shahad.o.data

import com.google.firebase.auth.AuthCredential
import com.shahad.o.data.dataSources.base.DataStoreDataSource
import com.shahad.o.data.dataSources.base.RemoteDataSource
import com.shahad.o.domain.Repository
import com.shahad.o.util.Record
import com.shahad.o.util.RecordResult
import com.shahad.o.util.SignInResult
import com.shahad.o.util.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RepositoryImp(
    private val datastoreDataSource: DataStoreDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun signOut() {
        remoteDataSource.signOut()
    }

    override fun googleSignIn(credential: AuthCredential): Flow<SignInResult> {
        return flow {
            val user = remoteDataSource.signIn(credential).user

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


    override fun getUser(): UserData? {
        return remoteDataSource.getUser()
    }

    override suspend fun getRecords(): List<Record> {
        return remoteDataSource.getRecords()
    }

    override fun sentResult(results: List<RecordResult>) {
        remoteDataSource.sentResult(results)
    }
}