package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository
import com.shahad.o.domain.models.DayResult
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant

class GetCalendar(
    private val repository: Repository
): BaseUseCase() {

    suspend fun getCalendar(year: Int , month: Int): List<DayResult>? {
        val startDate= LocalDateTime(year,month,1,0,0,0).toInstant(TimeZone.currentSystemDefault())
        val endDate= startDate.plus(1, DateTimeUnit.MONTH,TimeZone.currentSystemDefault())
        return repository.getCalendarData(startDate.toEpochMilliseconds(), endDate.toEpochMilliseconds())
    }
}