package com.shahad.o.data

import com.shahad.o.data.dataSources.base.DataStoreDataSource
import com.shahad.o.domain.Repository

class RepositoryImp(
    val datastoreDataSource: DataStoreDataSource
): Repository {
    override suspend fun getToken(): String? {
        return datastoreDataSource.getToken()
    }

    override suspend fun deleteToken() {
        return datastoreDataSource.deleteToken()
    }

    override suspend fun saveToken(token: String) {
        return datastoreDataSource.saveToken(token)
    }


}