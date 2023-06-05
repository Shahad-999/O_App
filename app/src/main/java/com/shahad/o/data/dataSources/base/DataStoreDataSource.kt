package com.shahad.o.data.dataSources.base

interface DataStoreDataSource {

    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun deleteToken()
}