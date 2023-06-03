package com.shahad.o.ui.navigation

sealed class  Screens(val route: String){
    object HomeScreen: Screens(HOME_SCREEN_ROUTE)
    object SplashScreen: Screens(SPLASH_SCREEN_ROUTE)
    object LoginScreen: Screens(LOGIN_SCREEN_ROUTE)
    companion object{
        private const val SPLASH_SCREEN_ROUTE = "splash"
        private const val LOGIN_SCREEN_ROUTE = "login"
        private const val HOME_SCREEN_ROUTE = "home"
    }
}