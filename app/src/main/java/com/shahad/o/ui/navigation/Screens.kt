package com.shahad.o.ui.navigation

sealed class  Screens(val route: String){
    object HomeScreen: Screens(HOME_SCREEN_ROUTE)
    object SplashScreen: Screens(SPLASH_SCREEN_ROUTE)
    object LoginScreen: Screens(LOGIN_SCREEN_ROUTE)
    object SettingScreen: Screens(SETTING_SCREEN_ROUTE)
    object RecordScreen: Screens(RECORD_SCREEN_ROUTE)

    companion object{
        private const val SPLASH_SCREEN_ROUTE = "splash"
        private const val LOGIN_SCREEN_ROUTE = "login"
        private const val HOME_SCREEN_ROUTE = "home"
        private const val SETTING_SCREEN_ROUTE = "home"
        private const val RECORD_SCREEN_ROUTE = "record"
    }
}