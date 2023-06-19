package com.shahad.o.util

data class SignInResult(
    val data: UserData?,
    val error: String?
)

data class UserData(
    val userId: String,
    val userName: String?,
    val profilePictureUrl: String?
) {
    companion object {
        val defaultUser = UserData(
            userId = "shahad.h.999.a@gmail.com",
            userName = "Shahad H.",
            profilePictureUrl = "https://avatars.githubusercontent.com/u/118618262?v=4"
        )
    }
}
