package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.RecordsUseCase
import com.shahad.o.domain.usecases.UpdateQuestionsUseCase
import com.shahad.o.ui.states.QuestionsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuestionsViewModel(
    private val recordsUseCase: RecordsUseCase,
    private val updateQuestionsUseCase: UpdateQuestionsUseCase,
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

    fun onQuestionChange(
        order: Int,
        question: String,
    ){
        val updatedList = (_records.value as QuestionsState.LoadedQuestions).questions.toList().apply {
            this[order].question = question
        }
        _records.value = QuestionsState.LoadedQuestions(updatedList)
    }
    fun onPositiveAnswerChange(
        order: Int,
        positiveAnswer: Boolean,
    ){
        val updatedList = (_records.value as QuestionsState.LoadedQuestions).questions.toList().apply {
            this[order].positive_answer = positiveAnswer
        }
        _records.value = QuestionsState.LoadedQuestions(updatedList)
    }

    fun onClickSave(){
        if(_records.value is QuestionsState.LoadedQuestions){
            updateQuestionsUseCase.updateQuestions((_records.value as QuestionsState.LoadedQuestions).questions)
        }
    }

}