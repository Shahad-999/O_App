package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.RecordsUseCase
import com.shahad.o.util.Record
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecordsViewModel(
    private val recordsUseCase: RecordsUseCase
) : ViewModel() {

    private val _records = MutableStateFlow<List<Record>>(emptyList())
    val records: StateFlow<List<Record>> = _records.asStateFlow()

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex = _currentIndex.asStateFlow()

    private val _isGoodDay = MutableSharedFlow<Boolean>()
    val isGoodDay = _isGoodDay.asSharedFlow()

    private lateinit var result: MutableMap<String, Boolean>

    init {
        getRecords()
    }

    private fun getRecords() {
        viewModelScope.launch {
            with(recordsUseCase.getRecords()) {
                _records.value = this
                result = this.associateBy({ it.question }, { false }).toMutableMap()
            }
        }
    }

    fun onClickYes() {
        updateResult(true)
        if (_currentIndex.value != _records.value.lastIndex) {
            _currentIndex.value++
        } else {
            onQuestionsEnded()
        }
    }

    fun onClickNo() {
        updateResult(false)
        if (_currentIndex.value != _records.value.lastIndex) {
            _currentIndex.value++
        } else {
            onQuestionsEnded()
        }
    }

    private fun onQuestionsEnded() {
        viewModelScope.launch {
            _isGoodDay.emit(recordsUseCase.isGoodDay(result.values.toList()))
            recordsUseCase.sendResult(result)
        }
    }

    private fun updateResult(state: Boolean) {
        result[_records.value[_currentIndex.value].question] = state
    }

}