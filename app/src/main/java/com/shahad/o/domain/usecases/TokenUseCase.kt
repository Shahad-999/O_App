package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository

class TokenUseCase(
    private val repository: Repository
): BaseUseCase() {

    suspend  fun getToken(): String?{
        return repository.getToken()
    }

    suspend fun saveToken(token: String){
        repository.saveToken(token)
    }

    suspend fun deleteToken(){
        repository.deleteToken()
    }
}