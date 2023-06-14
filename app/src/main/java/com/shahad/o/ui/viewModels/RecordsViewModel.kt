package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.R
import com.shahad.o.domain.usecases.RecordsUseCase
import com.shahad.o.ui.states.RecordScreenState
import com.shahad.o.util.Record
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecordsViewModel(
    private val recordsUseCase: RecordsUseCase
) : ViewModel() {

    private val _records = MutableStateFlow<RecordScreenState>(RecordScreenState.Initial)
    val records: StateFlow<RecordScreenState> = _records.asStateFlow()

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex = _currentIndex.asStateFlow()

    private lateinit var result: MutableMap<String, Boolean>

    init {
        getRecords()
    }

    private fun getRecords() {
        _records.value = RecordScreenState.LoadingQuestions
        viewModelScope.launch {
            with(recordsUseCase.getRecords()) {
                _records.value = RecordScreenState.LoadedQuestions(this)
                result = this.associateBy({ it.question }, { false }).toMutableMap()
            }
        }
    }

    fun onClickYes() {
        updateResult(true)
        moveToNextStep()
    }

    fun onClickNo() {
        updateResult(false)
        moveToNextStep()
    }

    private fun moveToNextStep(){
        if(_records.value is RecordScreenState.LoadedQuestions){
            if(isLastQuestion((_records.value as RecordScreenState.LoadedQuestions).questions)){
                _currentIndex.value++
            } else {
                onQuestionsEnded()
            }
        }
    }

    private fun isLastQuestion(questions: List<Record>) =  _currentIndex.value != questions.lastIndex

    private fun onQuestionsEnded() {
        viewModelScope.launch {
            _records.value = if(recordsUseCase.isGoodDay(result.values.toList())){
               RecordScreenState.Result(image = R.drawable.greate_day, text = R.string.great_day)
            }else {
                RecordScreenState.Result(image = R.drawable.white_heart, text = R.string.bad_day)
            }
            recordsUseCase.sendResult(result)
        }
    }

    private fun updateResult(state: Boolean) {
        if (_records.value is RecordScreenState.LoadedQuestions){
            result[(_records.value as RecordScreenState.LoadedQuestions).questions[_currentIndex.value].question] = state

        }
    }

}