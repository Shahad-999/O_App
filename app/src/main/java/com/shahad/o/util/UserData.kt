package com.shahad.o.util

data class SignInResult(
    val data: UserData?,
    val error: String?
)

data class UserData(
    val userId: String,
    val userName: String?,
    val profilePictureUrl: String?
)
