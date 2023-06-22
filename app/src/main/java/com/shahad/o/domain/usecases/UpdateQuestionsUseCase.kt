package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository
import com.shahad.o.util.Record

class UpdateQuestionsUseCase(
    private val repository: Repository
) : BaseUseCase() {

    fun updateQuestions(questions: List<Record>) {
        repository.updateQuestions(questions)
    }

}