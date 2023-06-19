package com.shahad.o.data.dataSources.base

import kotlinx.coroutines.flow.Flow

interface DataStoreDataSource {

    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun deleteToken()
    suspend fun updateMode(isDark: Boolean)
    fun isDarkMode(): Flow<Boolean>
}