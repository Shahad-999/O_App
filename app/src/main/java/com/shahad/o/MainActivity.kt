package com.shahad.o

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.viewModels.MainViewModel
import com.shahad.o.ui.views.screens.calendarRoute
import com.shahad.o.ui.views.screens.homeRoute
import com.shahad.o.ui.views.screens.loginRoute
import com.shahad.o.ui.views.screens.questionsRoute
import com.shahad.o.ui.views.screens.recordRoute
import com.shahad.o.ui.views.screens.settingRoute
import com.shahad.o.ui.views.screens.splashRoute
import com.shahad.o.ui.views.screens.statisticsRoute
import com.shahad.o.util.ReminderManger
import org.koin.androidx.compose.koinViewModel
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseMessaging.getInstance().subscribeToTopic("all")
        val reminderManger: ReminderManger by inject(ReminderManger::class.java)
        reminderManger.createNotificationsChannels()
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel()
) {
    val isDarkTheme by mainViewModel.isDarkMode.collectAsState()
    OTheme(isDarkTheme) {
        val navController = rememberNavController()
        Scaffold(
            Modifier.background(OTheme.colors.background)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screens.SplashScreen.route,
                modifier = Modifier.padding(it)
            ) {
                splashRoute(navController)
                homeRoute(navController)
                loginRoute(navController)
                recordRoute(navController)
                settingRoute(navController)
                questionsRoute(navController)
                calendarRoute(navController)
                statisticsRoute(navController)
            }
        }
    }
}
