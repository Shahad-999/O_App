package com.shahad.o.ui.states

import com.shahad.o.util.Record

sealed class QuestionsState{

    object Initial: QuestionsState()
    object LoadingQuestions: QuestionsState()
    class LoadedQuestions(val questions: List<Record>): QuestionsState()

}
