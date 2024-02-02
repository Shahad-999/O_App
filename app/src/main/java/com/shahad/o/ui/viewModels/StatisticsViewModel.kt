package com.shahad.o.ui.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.models.Statistics
import com.shahad.o.domain.usecases.GetStatisticUseCase
import com.shahad.o.util.getCurrentDate
import com.shahad.o.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import java.time.Month

@SuppressLint("NewApi")
class StatisticsViewModel(
    private val statisticUseCase: GetStatisticUseCase
) : ViewModel() {
    private val _startDate = MutableStateFlow<Instant>(getInitialYear())
    val startDate: Flow<LocalDate> =
        _startDate.map { it.toLocalDateTime(TimeZone.currentSystemDefault()).date }
    private val _endDate = MutableStateFlow<Instant>(getInitialYear(plus = 1))
    val endDate: Flow<LocalDate> =
        _endDate.map { it.toLocalDateTime(TimeZone.currentSystemDefault()).date }
    private val _statistics = MutableStateFlow<List<Statistics>>(emptyList())
    val statistics: StateFlow<List<Statistics>> = _statistics

    init {
        updateStatistics()
    }

    private fun updateStatistics() {
        viewModelScope.launch {
            _startDate.value.log()
            _endDate.value.log()
            statisticUseCase.call(_startDate.value.._endDate.value).log()
        }
    }

    fun setStartDate(date: LocalDate) {
        date.atStartOfDayIn(TimeZone.UTC).takeIf { it != _startDate.value }?.let {
            _startDate.value = it
            updateStatistics()
        }
    }

    fun setEndDate(date: LocalDate) {
        date.atStartOfDayIn(TimeZone.UTC).takeIf { it != _endDate.value }?.let {
            _endDate.value = it
            updateStatistics()
        }
    }

    private fun getInitialYear(plus: Int = 0): Instant {
        getCurrentDate().year.let {
            return LocalDateTime(
                year = it + plus,
                month = Month.JANUARY,
                dayOfMonth = 1,
                hour = 0,
                minute = 0
            ).toInstant(
                TimeZone.UTC
            )
        }
    }


}