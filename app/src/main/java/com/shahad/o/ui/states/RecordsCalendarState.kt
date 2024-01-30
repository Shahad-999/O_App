package com.shahad.o.ui.states

import com.shahad.o.domain.models.Result

data class RecordsCalendarState(
    val isLoading : Boolean = false,
    val records: List<Result>? = null,
    val isEmpty: Boolean = false,
    val isError: Boolean = false
)