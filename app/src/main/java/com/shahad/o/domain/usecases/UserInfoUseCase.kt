package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository
import com.shahad.o.util.UserData

class UserInfoUseCase(
    private val repository: Repository
): BaseUseCase() {
    fun getUser(): UserData?{
        return repository.getUser()
    }
}