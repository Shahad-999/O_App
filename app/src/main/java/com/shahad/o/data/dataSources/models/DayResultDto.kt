package com.shahad.o.data.dataSources.models

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class DayResultDto(
    val date: Long,
    val percent: Double,
    val results: List<ResultDto>
)
