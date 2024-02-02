package com.shahad.o.domain.models

import kotlinx.datetime.LocalDate

data class Statistics(
    val date: LocalDate,
    val percent: Double
)