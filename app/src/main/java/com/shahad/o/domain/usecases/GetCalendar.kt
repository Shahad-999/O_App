package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository
import com.shahad.o.domain.models.DayResult
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus

class GetCalendar(
    private val repository: Repository
): BaseUseCase() {

    suspend fun getCalendar(date:Instant): List<DayResult>? {
        val startDate= date.plus(3,DateTimeUnit.DAY,TimeZone.UTC).toEpochMilliseconds()
        val endDate= date.minus(3,DateTimeUnit.DAY,TimeZone.UTC).toEpochMilliseconds()
        return repository.getCalendarData(startDate, endDate)
    }
}