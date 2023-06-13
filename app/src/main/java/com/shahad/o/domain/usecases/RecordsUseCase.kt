package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository

class RecordsUseCase(
    private val repository: Repository
): BaseUseCase() {
    suspend fun  getRecords() = repository.getRecords()

    suspend fun sendResult(result: Map<String,Boolean>) {
    }

    private fun calcHappyPercent(result: List<Boolean>): Float {
        return result.filter { it }.size.toFloat()/result.size
    }

    fun isGoodDay(result: List<Boolean>): Boolean = calcHappyPercent(result) >= AVERAGE_HAPPINESS

    companion object{
        private const val AVERAGE_HAPPINESS = 0.5
    }
}