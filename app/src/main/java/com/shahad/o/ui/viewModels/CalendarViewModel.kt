package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.GetCalendar
import com.shahad.o.ui.states.MonthMoodsState
import com.shahad.o.ui.states.RecordsCalendarState
import com.shahad.o.util.getDays
import com.shahad.o.util.toEmoji
import com.shahad.o.util.toMonth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class CalendarViewModel(
    private val getCalendar: GetCalendar
) : ViewModel() {

    private val _year = MutableStateFlow(YEARS.first())
    val year: StateFlow<Int> = _year
    private val _monthIndex = MutableStateFlow(MONTHS.first())
    val monthIndex: StateFlow<Int> = _monthIndex
    val days = _monthIndex.map { it.getDays() }
    private val _dayIndex = MutableStateFlow(0)
    val dayIndex: StateFlow<Int> = _dayIndex
    private val _records = MutableStateFlow<RecordsCalendarState>(RecordsCalendarState())
    val records: StateFlow<RecordsCalendarState> = _records
    val months = MONTHS.map { it.toMonth() }
    val moods = MutableStateFlow(DEFAULT_MOODS)
    val results = MutableStateFlow<MonthMoodsState>(MonthMoodsState())

    init {
        viewModelScope.launch {
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).also { date ->
                _year.emit(date.year)
                _monthIndex.emit(date.monthNumber - 1)
                _dayIndex.emit(date.dayOfMonth - 1)
            }
        }
    }

    fun onMonthChanged(newMonthIndex: Int) {
        viewModelScope.launch {
            _monthIndex.emit(newMonthIndex)
        }
        resolveDifferentMonthSize(newMonthIndex)
        onDateChange()
    }

    private fun resolveDifferentMonthSize(monthIndex: Int) {
        if (_dayIndex.value == 30 && monthIndex.getDays().size == 30) _dayIndex.value = 29
        if (_dayIndex.value > 27 && monthIndex.getDays().size == 28) _dayIndex.value = 27

    }

    fun onDayChanged(newDayIndex: Int) {
        viewModelScope.launch {
            _dayIndex.emit(newDayIndex)
        }
        updateResult()
    }

    fun increaseYear() {
        viewModelScope.launch {
            _year.emit(++_year.value)
        }
        onDateChange()
    }

    fun decreaseYear() {
        viewModelScope.launch {
            _year.emit(--_year.value)
        }
        onDateChange()
    }


    private fun onDateChange(
        year: Int = _year.value,
        month: Int = _monthIndex.value + 1,
    ) {

        viewModelScope.launch {
            moods.tryEmit(DEFAULT_MOODS)
            _records.value = RecordsCalendarState(isLoading = true)
            results.value = MonthMoodsState(isLoading = true)
            val calendarResults =getCalendar.getCalendar(year, month)
            if(calendarResults!=null){
                dayToMode(calendarResults.associateBy(
                    {
                        Instant.fromEpochMilliseconds(it.date)
                            .toLocalDateTime(TimeZone.currentSystemDefault()).dayOfMonth
                    }, { it.percent }
                )
                )
                results.tryEmit(MonthMoodsState(records= calendarResults.associateBy({
                    Instant.fromEpochMilliseconds(it.date)
                        .toLocalDateTime(TimeZone.currentSystemDefault()).dayOfMonth
                }, { it.results })))
                updateResult()
            }else{
                _records.value = RecordsCalendarState(isError = true)
                results.value = MonthMoodsState(isError = true)
            }

        }
    }

    private fun updateResult() {
        val results = results.value
        if(results.isError){
            _records.value = RecordsCalendarState(isEmpty = true)

        }else if(results.isLoading){
            _records.value = RecordsCalendarState(isLoading = true)

        }else if(results.records!=null) {

                if (results.records[_dayIndex.value + 1]==null) {
                    _records.value = RecordsCalendarState(isEmpty = true)
                } else {
                    _records.value = RecordsCalendarState(records = results.records[_dayIndex.value + 1])
                }
            }

    }

    companion object {
        val YEARS = (1999..2100).toList()
        val MONTHS = (1..12).toList()
        val DEFAULT_MOODS = List(31) { "⚫" }
    }

    private fun dayToMode(activeDays: Map<Int, Double>) {
        moods.tryEmit(List(31) { index -> if (activeDays.containsKey(index + 1)) activeDays[index + 1]!!.toEmoji() else "⚫" })
    }
}
