package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.RecordsUseCase
import com.shahad.o.ui.states.QuestionsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuestionsViewModel(
    private val recordsUseCase: RecordsUseCase,
) : ViewModel() {

    private val _records = MutableStateFlow<QuestionsState>(QuestionsState.Initial)
    val records: StateFlow<QuestionsState> = _records.asStateFlow()

    init {
        getRecords()
    }

    private fun getRecords() {
        _records.value = QuestionsState.LoadingQuestions
        viewModelScope.launch {
            with(recordsUseCase.getRecords()) {
                _records.value = QuestionsState.LoadedQuestions(this)
            }
        }
    }

}