package com.shahad.o.util

data class  RecordResult(
    val question: String,
    val weight: Long,
    val isPositive: Boolean,
    val answer: String,
    val isAnswerPositive: Boolean
)

data class Results(
    val records: List<RecordResult>,
    val date: Long,
    val percent: Float
)