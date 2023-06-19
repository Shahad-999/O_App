package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.ThemeUseCase
import com.shahad.o.domain.usecases.UserInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingViewModel(
    userInfoUseCase: UserInfoUseCase,
    private val themeUseCase: ThemeUseCase
) : ViewModel() {

    val userData = userInfoUseCase.getUser()
    private val _isDarkMode = MutableStateFlow(true)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode

    init {
        fetchThemeMode()
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
}