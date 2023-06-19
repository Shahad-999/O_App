package com.shahad.o.ui.viewModels

import androidx.lifecycle.ViewModel
import com.shahad.o.domain.usecases.UserInfoUseCase

class SettingViewModel(
    userInfoUseCase: UserInfoUseCase
) : ViewModel() {

    val userData = userInfoUseCase.getUser()
}