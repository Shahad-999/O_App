package com.shahad.o.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.ui.views.screens.HomeScreen
import com.shahad.o.ui.views.screens.LoginScreen
import com.shahad.o.ui.views.screens.SplashScreen

fun NavGraphBuilder.splashRoute(
    navController: NavHostController
) {
    fun navToHome() = navController.navigate(Screens.HomeScreen.route) {
        popUpTo(Screens.SplashScreen.route) {
            inclusive = true
        }
    }

    fun navToLogin() = navController.navigate(Screens.LoginScreen.route) {
        popUpTo(Screens.SplashScreen.route) {
            inclusive = true
        }
    }

    composable(Screens.SplashScreen.route) {
        SplashScreen(
            navController = navController,
            navToHome = ::navToHome,
            navToLogin = ::navToLogin

        )
    }
}

fun NavGraphBuilder.homeRoute(
    navController: NavHostController
) {
    composable(Screens.HomeScreen.route) {
        HomeScreen(navController = navController)
    }
}

fun NavGraphBuilder.loginRoute(
    navController: NavHostController
) {
    composable(Screens.LoginScreen.route) {
        LoginScreen(
            navController = navController,
        )
    }
}
