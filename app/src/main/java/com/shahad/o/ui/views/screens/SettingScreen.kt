package com.shahad.o.ui.views.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.shahad.o.R
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.util.animatedComposable
import com.shahad.o.ui.util.go
import com.shahad.o.ui.viewModels.SettingViewModel
import com.shahad.o.ui.views.widgets.NavigationAppBar
import com.shahad.o.ui.views.widgets.SettingBody
import com.shahad.o.util.UserData
import com.shahad.o.util.showToast
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.settingRoute(
    navController: NavHostController
) {

    fun navToQuestionScreen() = navController.navigate(Screens.QuestionsScreen.route)
    fun navToCalendarScreen() = navController.navigate(Screens.CalendarScreen.route)
    fun navToStatisticsScreen() = navController.navigate(Screens.StatisticsScreen.route)
    fun goToLoginScreen() = navController.go(Screens.SettingScreen, Screens.LoginScreen)

    animatedComposable(Screens.SettingScreen.route) {
        SettingScreen(
            backToHome = navController::navigateUp,
            navToQuestionsScreen = ::navToQuestionScreen,
            navToCalendarScreen = ::navToCalendarScreen,
            navToStatisticsScreen = ::navToStatisticsScreen,
            goToLoginScreen = ::goToLoginScreen
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
    viewModel: SettingViewModel = koinViewModel(),
    navToQuestionsScreen: () -> Unit,
    navToCalendarScreen: () -> Unit,
    navToStatisticsScreen: () -> Unit,
    goToLoginScreen: () -> Unit,
) {
    val isSignOut by viewModel.isSignOut.collectAsState()
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            context.showToast("ستتلقى اشعارات يوميه")
        } else if (viewModel.isNotificationsOn.value) {

            context.showToast("يجب اعطاء صلاحيه لتلقي الاشعارات")
        }
    }


    Scaffold(
        modifier = modifier,
        topBar = {
            NavigationAppBar(
                text = stringResource(R.string.settings),
                backToHome = backToHome,
            )
        },
        content = {
            SettingBody(
                modifier = Modifier.padding(it),
                userInfo = viewModel.userData ?: UserData.defaultUser,
                isDarkTheme = viewModel.isDarkMode,
                inThemeChange = viewModel::updateTheme,
                isNotificationsOn = viewModel.isNotificationsOn,
                onNotificationsStatusChange = viewModel::updateNotificationsStatus,
                onClickQuestions = navToQuestionsScreen,
                onClickCalendar = navToCalendarScreen,
                onClickSignOut = viewModel::onClickSignOut,
                onClickStatistics = navToStatisticsScreen
            )
        },
        containerColor = OTheme.colors.background,
    )

    LaunchedEffect(Unit) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) -> {


            }

            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }
    LaunchedEffect(isSignOut) {
        if (isSignOut) {
            goToLoginScreen()
        }
    }
}

