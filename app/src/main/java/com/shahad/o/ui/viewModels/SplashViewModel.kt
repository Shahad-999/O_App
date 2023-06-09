package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import com.shahad.o.domain.usecases.UserInfoUseCase
import com.shahad.o.ui.util.UserState

class SplashViewModel(
    private val userInfoUseCase: UserInfoUseCase
) : ViewModel() {

    var state = UserState.Initial
        private set


    init {
        state = if (userInfoUseCase.getUser() == null) {
            UserState.NotFounded
        } else {
            UserState.Founded
        }
    }

}