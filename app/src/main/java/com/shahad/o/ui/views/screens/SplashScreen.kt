package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.util.UserState
import com.shahad.o.ui.util.animatedComposable
import com.shahad.o.ui.util.go
import com.shahad.o.ui.viewModels.SplashViewModel
import com.shahad.o.ui.views.widgets.SplashBody
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.splashRoute(
    navController: NavHostController
) {
    fun navToHome() =
        navController.go(source = Screens.SplashScreen, destination = Screens.HomeScreen)

    fun navToLogin() =
        navController.go(source = Screens.SplashScreen, destination = Screens.LoginScreen)

    animatedComposable(
        Screens.SplashScreen.route,
    ) {
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
    val state = viewModel.state
    SplashBody(
        modifier = modifier,
        onCompleteFlipping = {
            when (state) {
                UserState.Initial -> {}
                UserState.Founded -> navToHome()
                UserState.NotFounded -> navToLogin()
            }
        }
    )
}
