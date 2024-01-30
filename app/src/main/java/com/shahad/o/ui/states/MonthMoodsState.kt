package com.shahad.o.ui.states

import com.shahad.o.domain.models.Result

data class MonthMoodsState(
    val isLoading : Boolean = false,
    val records: Map<Int,List<Result>>? = null,
    val isError: Boolean = false
)