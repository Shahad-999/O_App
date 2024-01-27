package com.shahad.o.data.dataSources.models

data class DayResultDto(
    val date: Long,
    val percent: Double,
    val results: List<ResultDto>
)
