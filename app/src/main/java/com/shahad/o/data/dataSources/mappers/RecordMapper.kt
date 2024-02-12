package com.shahad.o.data.dataSources.mappers

import com.shahad.o.util.Record

fun List<Record>.toFirebaseDto(): HashMap<String, List<HashMap<String, Any>>> {
    return hashMapOf(
        "questions" to map {
            hashMapOf(
                "text" to it.question,
                "image" to it.imageUrl,
                "order" to it.order,
                "weight" to it.weight,
                "positive_answer" to it.positiveAnswer
            )
        }
    )
}


fun Map<String, Any>.toRecords(): List<Record> {
    return (this["questions"] as List<Map<String, Any>>).mapIndexed { index, map ->
        Record(
            order = index,
            question = (map["text"] ?: "") as String,
            imageUrl = (map["image"] ?: "") as String,
            weight = (map["weight"] ?: 1) as Long,
            positiveAnswer = (map["positive_answer"] ?: true) as Boolean
        )
    }
}

