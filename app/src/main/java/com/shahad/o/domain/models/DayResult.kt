package com.shahad.o.domain.models

data class DayResult(
    val date: Long,
    val percent: Double,
    val results: List<Result>
)
