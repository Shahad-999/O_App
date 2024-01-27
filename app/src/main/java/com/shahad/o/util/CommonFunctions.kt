package com.shahad.o.util

import kotlinx.datetime.Clock

fun getCurrentDate(): Long = Clock.System.now().atStartOfDay().toEpochMilliseconds()
