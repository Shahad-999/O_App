package com.shahad.o.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentMillSecond(): Long = Clock.System.now().atStartOfDay().toEpochMilliseconds()
fun getCurrentDate(): LocalDate =  Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
