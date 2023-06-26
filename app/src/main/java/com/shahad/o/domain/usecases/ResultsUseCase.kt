package com.shahad.o.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.shahad.o.domain.Repository
import com.shahad.o.util.RecordResult
import com.shahad.o.util.Results
import com.shahad.o.util.log
import java.time.LocalDate
import java.time.ZoneOffset

class ResultsUseCase(
    private val repository: Repository
) : BaseUseCase() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendResult(results: List<RecordResult>) {
        repository.sentResult(
            Results(
                records = results,
                date = LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli(),
                percent = calcHappyPercent(results)
            ))
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