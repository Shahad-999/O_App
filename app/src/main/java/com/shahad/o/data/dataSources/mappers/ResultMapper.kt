package com.shahad.o.data.dataSources.mappers

import com.shahad.o.util.Results

fun Results.toFirebaseDto(): HashMap<String, Any> {
    val results = records.map {
        return@map hashMapOf<String, Any>(
            "isPositive" to it.isPositive,
            "question" to it.question,
            "weight" to it.weight,
            "answer" to it.answer
        )
    }
    return hashMapOf(
        "results" to results,
        "date" to date,
        "percent" to percent
    )
}