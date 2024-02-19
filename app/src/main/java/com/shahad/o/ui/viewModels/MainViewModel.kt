package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.CheckTodayStatusUseCase
import com.shahad.o.domain.usecases.NotificationsUseCase
import com.shahad.o.domain.usecases.ThemeUseCase
import com.shahad.o.domain.usecases.UserInfoUseCase
import com.shahad.o.ui.util.UserState
import com.shahad.o.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val themeUseCase: ThemeUseCase,
    private val notificationsUseCase: NotificationsUseCase,
    private val userInfoUseCase: UserInfoUseCase,
) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode


    var state = UserState.Initial
        private set

    init {
        checkAuth()
        fetchThemeMode()
        setupNotifications()
    }


    private fun checkAuth() {
        state = if (userInfoUseCase.getUser() == null) {
            UserState.NotFounded
        } else {
            UserState.Founded
        }
    }


    private fun setupNotifications(){
        viewModelScope.launch {
            notificationsUseCase.isNotificationsOn().collect{ isTurnOn ->
                "Notification is $isTurnOn ".log("FROM_VIEWMODEL")
                notificationsUseCase.updateNotificationStatus(isTurnOn)
            }
        }
    }

    private fun fetchThemeMode() {
        viewModelScope.launch {
            themeUseCase.isDark().collect {
                if(it!=_isDarkMode.value){
                    it.log("FROM_VIEWMODEL")
                    _isDarkMode.value = it
                }
            }
        }
    }

}