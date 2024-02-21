package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.NotificationsUseCase
import com.shahad.o.domain.usecases.SignOutUseCase
import com.shahad.o.domain.usecases.ThemeUseCase
import com.shahad.o.domain.usecases.UserInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingViewModel(
    userInfoUseCase: UserInfoUseCase,
    private val themeUseCase: ThemeUseCase,
    private val notificationsUseCase: NotificationsUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    val userData = userInfoUseCase.getUser()
    private val _isDarkMode = MutableStateFlow<Boolean?>(null)
    val isDarkMode: StateFlow<Boolean?> = _isDarkMode

    private val _isNotificationsOn = MutableStateFlow(true)
    val isNotificationsOn: StateFlow<Boolean> = _isNotificationsOn

    private val _isSignOut = MutableStateFlow(false)
    val isSignOut: StateFlow<Boolean> = _isSignOut

    init {
        fetchThemeMode()
        fetchNotificationStatus()
    }

    private fun fetchThemeMode() {
        viewModelScope.launch {
            themeUseCase.isDark().collect{
                _isDarkMode.value = it
            }
        }
    }

    fun updateTheme(isDark: Boolean){
        viewModelScope.launch {
            themeUseCase.updateMode(isDark)
        }
    }
    private fun fetchNotificationStatus() {
        viewModelScope.launch {
            notificationsUseCase.isNotificationsOn().collect{
                _isNotificationsOn.value = it
            }
        }
    }

    fun updateNotificationsStatus(isTurnOn: Boolean){
        viewModelScope.launch {
            notificationsUseCase.storeNotificationsStatus(isTurnOn)
        }
    }

    fun onClickSignOut(){
        signOutUseCase.signOut()
        _isSignOut.value = true
    }
}