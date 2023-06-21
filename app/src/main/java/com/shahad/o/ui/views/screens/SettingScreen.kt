package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.viewModels.SettingViewModel
import com.shahad.o.ui.views.widgets.NavigationAppBar
import com.shahad.o.ui.views.widgets.SettingBody
import com.shahad.o.util.UserData
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.settingRoute(
    navController: NavHostController
) {

    composable(Screens.SettingScreen.route) {
        SettingScreen(
            backToHome = navController::navigateUp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
    viewModel: SettingViewModel = koinViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = { NavigationAppBar(backToHome = backToHome) },
        content = {
            SettingBody(
                modifier = Modifier.padding(it),
                userInfo = viewModel.userData ?: UserData.defaultUser,
                isDarkTheme = viewModel.isDarkMode,
                inThemeChange = viewModel::updateTheme,
                isNotificationsOn = viewModel.isNotificationsOn,
                onNotificationsStatusChange = viewModel::updateNotificationsStatus
            )
        },
        containerColor = OTheme.colors.background,
    )
}
