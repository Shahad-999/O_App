package com.shahad.o.data.dataSources.mappers

import com.shahad.o.data.dataSources.models.DayResultDto
import com.shahad.o.data.dataSources.models.ResultDto
import com.shahad.o.domain.models.DayResult
import com.shahad.o.domain.models.Result
@JvmName("toDayResultModel")
fun DayResultDto.toModel(): DayResult {
    return DayResult(
        date = date, percent = percent, results = results.toModel()
    )
}

@JvmName("toListOfDayResultModel")
fun List<DayResultDto>.toModel(): List<DayResult> = this.map { it.toModel() }


@JvmName("toResultModel")
fun ResultDto.toModel(): Result {
    return Result(
        isPositive = isPositive,
        question = question,
        weight = weight,
        answer = answer
    )
}

@JvmName("toListOfResultModel")
fun List<ResultDto>.toModel(): List<Result> = this.map { it.toModel() }