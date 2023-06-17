package com.shahad.o.util

data class Record(
    val order: Int,
    val question: String,
    val imageUrl: String,
    val positive_answer: Boolean,
    val weight: Long,
)