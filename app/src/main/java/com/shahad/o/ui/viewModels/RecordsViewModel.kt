package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.util.Record
import com.shahad.o.domain.usecases.RecordsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecordsViewModel(
    private val recordsUseCase: RecordsUseCase
) : ViewModel() {

    val records = MutableStateFlow<List<Record>>(emptyList())

    val currentIndex = MutableStateFlow<Int>(0)
    init {
        viewModelScope.launch {
            records.value = recordsUseCase.getRecords()
        }
    }
}