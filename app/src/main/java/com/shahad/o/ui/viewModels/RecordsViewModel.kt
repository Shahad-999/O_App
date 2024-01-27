package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.R
import com.shahad.o.domain.usecases.RecordsUseCase
import com.shahad.o.domain.usecases.ResultsUseCase
import com.shahad.o.ui.states.RecordScreenState
import com.shahad.o.util.Record
import com.shahad.o.util.RecordResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecordsViewModel(
    private val recordsUseCase: RecordsUseCase,
    private val resultsUseCase: ResultsUseCase,
) : ViewModel() {

    private val _records = MutableStateFlow<RecordScreenState>(RecordScreenState.Initial)
    val records: StateFlow<RecordScreenState> = _records.asStateFlow()

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex = _currentIndex.asStateFlow()

    private var results: MutableList<RecordResult> = mutableListOf()

    init {
        getRecords()
    }

    private fun getRecords() {
        _records.value = RecordScreenState.LoadingQuestions
        viewModelScope.launch {
            with(recordsUseCase.getRecords()) {
                _records.value = RecordScreenState.LoadedQuestions(this)
            }
        }
    }

    fun onClickYes() {
        updateResult(true,"YES")
        moveToNextStep()
    }

    fun onClickNo() {
        updateResult(false,"NO")
        moveToNextStep()
    }

    private fun moveToNextStep() {
        if (_records.value is RecordScreenState.LoadedQuestions) {
            if (isLastQuestion((_records.value as RecordScreenState.LoadedQuestions).questions)) {
                onQuestionsEnded()
            } else {
                _currentIndex.value++
            }
        }
    }

    private fun isLastQuestion(questions: List<Record>) = _currentIndex.value == questions.lastIndex

    private fun onQuestionsEnded() {
        viewModelScope.launch {
            _records.value = if (resultsUseCase.isGoodDay(results)) {
                RecordScreenState.Result(image = R.drawable.greate_day, text = R.string.great_day)
            } else {
                RecordScreenState.Result(image = R.drawable.white_heart, text = R.string.bad_day)
            }
            resultsUseCase.sendResult(results)
        }
    }

    private fun updateResult(
        state: Boolean,
        answer: String
    ) {
        if (_records.value is RecordScreenState.LoadedQuestions) {
            with((_records.value as RecordScreenState.LoadedQuestions).questions[_currentIndex.value]) {
                results.add(
                    RecordResult(
                        question = this.question,
                        weight = this.weight,
                        isPositive = this.positive_answer,
                        answer = answer,
                        isAnswerPositive =  this.positive_answer == state
                    )
                )
            }
        }
    }

}