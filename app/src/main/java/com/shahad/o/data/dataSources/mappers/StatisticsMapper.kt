package com.shahad.o.data.dataSources.mappers

import com.shahad.o.domain.models.Statistics
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Map<Long, Double>.toStatistics(): List<Statistics> {
    return this.map {
        Statistics(
            date = Instant.fromEpochMilliseconds(it.key)
                .toLocalDateTime(TimeZone.currentSystemDefault()).date,
            percent = it.value
        )
    }
}