package com.shahad.o.data.dataSources.models


data class ResultDto(
    val isPositive: Boolean,
    val question: String,
    val weight: Int,
    val answer: String
)