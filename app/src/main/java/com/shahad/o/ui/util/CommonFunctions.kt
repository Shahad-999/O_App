package com.shahad.o.ui.util

import kotlinx.datetime.Clock

fun getCurrentDate(): Long = Clock.System.now().atStartOfDay().toEpochMilliseconds()
