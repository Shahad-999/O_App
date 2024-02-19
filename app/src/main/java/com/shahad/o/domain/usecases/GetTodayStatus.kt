package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository
import kotlinx.coroutines.flow.Flow

class GetTodayStatus(
    private val repository: Repository
): BaseUseCase() {

     fun get(): Flow<Boolean> {
        return repository.getTodayStatus()
    }
}