package com.shahad.o.domain

interface Repository {
    suspend fun getToken(): String?
    suspend fun saveToken(token: String)
    suspend fun deleteToken()
}