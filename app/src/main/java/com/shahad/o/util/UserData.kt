package com.shahad.o.util

data class SignInResult(
    val data: UserData?,
    val error: String?
)

data class UserData(
    val userId: String,
    val userName: String?,
    val profilePictureUrl: String?,
    val email: String?
) {
    companion object {
        val defaultUser = UserData(
            userId = "Not Founded",
            userName = "UNKNOWN USER",
            profilePictureUrl = "https://avatars.githubusercontent.com/u/118618262?v=4",
            email = null
        )
    }
}
