package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository

class SignOutUseCase(
    private val repository: Repository
) : BaseUseCase() {

    fun signOut() {
        return repository.signOut()
    }
}