package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.ui.util.GoogleAuthUiClient
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.viewModels.LoginViewModel
import com.shahad.o.ui.views.widgets.LoginBody
import com.shahad.o.ui.util.go
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.loginRoute(
    navController: NavHostController
) {
    fun navToHome() =
        navController.go(source = Screens.LoginScreen, destination = Screens.HomeScreen)

    composable(Screens.LoginScreen.route) {
        LoginScreen(
            navToHome = ::navToHome
        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
    navToHome: () -> Unit
) {

    val context = LocalContext.current
    val state by viewModel.signInState.collectAsState()
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.data != null) {
                googleAuthUiClient.getSignInResult(result.data!!, viewModel::googleSignIn)
            }

        }
    )


    val onClickGoogleSignIn = {
        launcher.launch(googleAuthUiClient.getClient().signInIntent)
    }

    LoginBody(
        modifier = modifier,
        onClickSignUp = onClickGoogleSignIn,
    )

    LaunchedEffect(key1 = state) {
        if (state.isSuccess) {
            navToHome()
        }
    }
}
