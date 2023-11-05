package com.shahad.o.ui.util

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime


fun Instant.atStartOfDay(timeZone: TimeZone = TimeZone.currentSystemDefault()): Instant {
    this.toLocalDateTime(timeZone).apply {
        return LocalDateTime(year, monthNumber,dayOfMonth,0,0,0).toInstant(TimeZone.UTC)
    }
}