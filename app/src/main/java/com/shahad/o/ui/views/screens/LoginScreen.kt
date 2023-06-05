package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.viewModels.LoginViewModel
import com.shahad.o.ui.views.widgets.LoginBody
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.loginRoute(
    navController: NavHostController
) {
    composable(Screens.LoginScreen.route) {
        LoginScreen(

        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
) {
    LoginBody(
        modifier = modifier
    )
}
