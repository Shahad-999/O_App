package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository
import com.shahad.o.util.RecordResult
import com.shahad.o.util.log

class RecordsUseCase(
    private val repository: Repository
) : BaseUseCase() {
    suspend fun getRecords() = repository.getRecords()

    fun sendResult(results: List<RecordResult>) {
        repository.sentResult(results)
    }

    private fun calcHappyPercent(results: List<RecordResult>): Float {
        val list: MutableList<Boolean> = mutableListOf()
        results.forEach { result ->
            repeat(result.weight.toInt()) {
                list.add(result.isPositive)
            }
        }
        return list.filter { it }.size.toFloat() / list.size
    }

    fun isGoodDay(result: List<RecordResult>): Boolean =
        calcHappyPercent(result).log()!! >= AVERAGE_HAPPINESS

    companion object {
        private const val AVERAGE_HAPPINESS = 0.5
    }
}