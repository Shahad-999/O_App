package com.shahad.o.data.dataSources.base


interface RemoteDataSource {
    fun signUp(): String
    fun login(token: String)
}