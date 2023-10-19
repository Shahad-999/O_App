package com.shahad.o.data.dataSources.models

import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    val isPositive: Boolean,
    val question: String,
    val weight: Int
)