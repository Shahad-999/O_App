package com.shahad.o.ui.states

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.shahad.o.util.Record

sealed class RecordScreenState {
    data object Initial: RecordScreenState()
    data object LoadingQuestions: RecordScreenState()
    class LoadedQuestions(val questions: List<Record>): RecordScreenState()
    class Result(@DrawableRes val image: Int,@StringRes val text: Int): RecordScreenState()
}