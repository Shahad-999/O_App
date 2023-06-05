package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.viewModels.SplashViewModel
import com.shahad.o.ui.views.widgets.SplashBody
import com.shahad.o.util.go
import org.koin.androidx.compose.koinViewModel


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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = koinViewModel(),
    navToHome: () -> Unit,
    navToLogin: () -> Unit,
) {
    SplashBody(
        modifier = modifier
    )
    LaunchedEffect(true) {
        viewModel.token.collect { token ->
            if (token == null) {
                navToLogin()
            } else {
                Log.i("TOKEN", token)
                navToHome()
            }

        }

    }
}
