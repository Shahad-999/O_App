package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahad.o.domain.usecases.UserInfoUseCase
import com.shahad.o.ui.util.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val userInfoUseCase: UserInfoUseCase
) : ViewModel() {

    val state = MutableStateFlow(UserState.Initial)


    init {
        viewModelScope.launch {
            state.value = if (userInfoUseCase.getUser() == null) {
                UserState.NotFounded
            } else {
                UserState.Founded
            }
        }
    }

}