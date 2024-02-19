package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository

class CheckTodayStatusUseCase(
    private val repository: Repository
): BaseUseCase() {

     suspend fun check() {
        return repository.checkTodayRecordStatus()
    }
}