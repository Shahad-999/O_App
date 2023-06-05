package com.shahad.o.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.ui.views.screens.HomeScreen
import com.shahad.o.ui.views.screens.LoginScreen
import com.shahad.o.ui.views.screens.SplashScreen
import com.shahad.o.util.go

fun NavGraphBuilder.splashRoute(
    navController: NavHostController
) {
    fun navToHome() =
        navController.go(source = Screens.SplashScreen, destination = Screens.HomeScreen)

    fun navToLogin() =
        navController.go(source = Screens.SplashScreen, destination = Screens.LoginScreen)

    composable(Screens.SplashScreen.route) {
        SplashScreen(
            navToHome = ::navToHome,
            navToLogin = ::navToLogin
        )
    }
}

fun NavGraphBuilder.homeRoute(
    navController: NavHostController
) {
    composable(Screens.HomeScreen.route) {
        HomeScreen()
    }
}

fun NavGraphBuilder.loginRoute(
    navController: NavHostController
) {
    composable(Screens.LoginScreen.route) {
        LoginScreen(

        )
    }
}
