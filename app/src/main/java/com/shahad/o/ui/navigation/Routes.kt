package com.shahad.o.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.ui.views.screens.SplashScreen

fun NavGraphBuilder.splashRoute(
    navController: NavHostController
){
    composable(Screens.SplashScreen.route){
        SplashScreen(navController = navController)
    }
}
