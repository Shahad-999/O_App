package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.NotificationsUseCase
import com.shahad.o.domain.usecases.ThemeUseCase
import com.shahad.o.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val themeUseCase: ThemeUseCase,
    private val notificationsUseCase: NotificationsUseCase
) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(true)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    init {
        fetchThemeMode()
        setupNotifications()
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
                it.log("FROM_VIEWMODEL")
                _isDarkMode.value = it
            }
        }
    }

}