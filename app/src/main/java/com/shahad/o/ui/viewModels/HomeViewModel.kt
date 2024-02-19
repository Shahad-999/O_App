package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.CheckTodayStatusUseCase
import com.shahad.o.domain.usecases.GetTodayStatus
import com.shahad.o.domain.usecases.UserInfoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userInfoUseCase: UserInfoUseCase,
    private val todayStatusUseCase: GetTodayStatus,
    private  val checkTodayStatusUseCase: CheckTodayStatusUseCase
) : ViewModel() {

    val userData = userInfoUseCase.getUser()
    init {
        checkTodayStatus()
    }
    private fun checkTodayStatus() {
        viewModelScope.launch {
            checkTodayStatusUseCase.check()
        }
    }
    val todayStatus = todayStatusUseCase.get().stateIn(viewModelScope,  started = SharingStarted.Companion.Lazily,initialValue = false)
}