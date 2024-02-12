package com.shahad.o.ui.navigation

sealed class  Screens(val route: String){
    data object HomeScreen: Screens(HOME_SCREEN_ROUTE)
    data object SplashScreen: Screens(SPLASH_SCREEN_ROUTE)
    data object LoginScreen: Screens(LOGIN_SCREEN_ROUTE)
    data object SettingScreen: Screens(SETTING_SCREEN_ROUTE)
    data object RecordScreen: Screens(RECORD_SCREEN_ROUTE)
    data object QuestionsScreen: Screens(QUESTIONS_SCREEN_ROUTE)
    data object CalendarScreen: Screens(CALENDAR_SCREEN_ROUTE)
    data object StatisticsScreen: Screens(STATISTICS_SCREEN_ROUTE)

    companion object{
        private const val SPLASH_SCREEN_ROUTE = "splash"
        private const val LOGIN_SCREEN_ROUTE = "login"
        private const val HOME_SCREEN_ROUTE = "home"
        private const val SETTING_SCREEN_ROUTE = "setting"
        private const val RECORD_SCREEN_ROUTE = "record"
        private const val QUESTIONS_SCREEN_ROUTE = "questions"
        private const val CALENDAR_SCREEN_ROUTE = "calendar"
        private const val STATISTICS_SCREEN_ROUTE = "statistics"
    }
}