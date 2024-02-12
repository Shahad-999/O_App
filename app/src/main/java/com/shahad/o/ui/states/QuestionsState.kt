package com.shahad.o.ui.states

import com.shahad.o.util.Record

sealed class QuestionsState{

    data object Initial: QuestionsState()
    data object LoadingQuestions: QuestionsState()
    class LoadedQuestions(val questions: List<Record>): QuestionsState()

}
