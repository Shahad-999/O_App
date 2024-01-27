package com.shahad.o.domain.models

data class Result(
    val isPositive: Boolean,
    val question: String,
    val weight: Int,
    val answer: String
)