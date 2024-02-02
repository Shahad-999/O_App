package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository
import com.shahad.o.domain.models.Statistics
import com.shahad.o.util.toEpochMillisecondsRange
import kotlinx.datetime.Instant

class GetStatisticUseCase(
    private val repository: Repository
) : BaseUseCase() {

    suspend fun call(dateRange: ClosedRange<Instant>): List<Statistics>? {
        return repository.getStatistics(
            dateRange.toEpochMillisecondsRange()
        )
    }

}