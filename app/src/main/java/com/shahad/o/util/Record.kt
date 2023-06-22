package com.shahad.o.util

data class Record(
    val order: Int,
    var question: String,
    val imageUrl: String,
    var positive_answer: Boolean,
    val weight: Long,
)