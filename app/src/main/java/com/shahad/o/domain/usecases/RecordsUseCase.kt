package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository

class RecordsUseCase(
    private val repository: Repository
): BaseUseCase() {
    suspend fun  getRecords() = repository.getRecords()
}