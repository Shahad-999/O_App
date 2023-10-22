package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.GetCalendar
import com.shahad.o.util.getDays
import com.shahad.o.util.log
import com.shahad.o.util.toMonth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
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
    private val _date = MutableStateFlow<String>("")
    val date: StateFlow<String> = _date
    val months = MONTHS.map { it.toMonth() }
    val moods = MutableStateFlow(listOf(
            "😄", "😃", "😊", "😁", "😆",
            "😌", "😍", "🤗", "😇", "🙂",
            "😎", "🙏", "💪", "🚀", "🎉",
            "😐", "😬", "😰", "😴", "😵",
            "😢", "😡", "😤", "😫", "😕",
            "❤️", "😔", "🙌", "😣", "🔥",
            "😲"
    ))

    init {
        viewModelScope.launch {
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).also {date ->
                _year.emit(date.year)
                _monthIndex.emit(date.monthNumber - 1)
                _dayIndex.emit( date.dayOfMonth - 1)
            }
        }
    }

    fun onMonthChanged(newMonthIndex: Int){
        viewModelScope.launch {
            _monthIndex.emit(newMonthIndex)
        }
        resolveDifferentMonthSize(newMonthIndex)
        onDateChange()
    }

    private fun resolveDifferentMonthSize(monthIndex: Int){
        if(_dayIndex.value==30 && monthIndex.getDays().size == 30) _dayIndex.value = 29
        if(_dayIndex.value > 27 && monthIndex.getDays().size == 28) _dayIndex.value = 27

    }
    fun onDayChanged(newDayIndex: Int){
        viewModelScope.launch {
            _dayIndex.emit(newDayIndex)
        }
        onDateChange()
        "view model detect the current day is ${newDayIndex + 1}".log()
    }

    fun increaseYear() {
        viewModelScope.launch {
            _year.emit(++_year.value)
        }
        onDateChange()
    }

    fun decreaseYear(){
        viewModelScope.launch {
            _year.emit(--_year.value)
        }
        onDateChange()
    }
    private fun onDateChange(
        year: Int= _year.value,
        month: Int=_monthIndex.value+1,
        day: Int = _dayIndex.value +1
    ){

        LocalDate(year= year, monthNumber = month, dayOfMonth = day).toString().apply {
            if(this != _date.value) _date.value = this
        }
    }
    companion object{
        val YEARS = (1999..2100).toList()
        val MONTHS = (1..12).toList()
    }

}