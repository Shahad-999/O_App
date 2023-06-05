package com.shahad.o

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.views.screens.homeRoute
import com.shahad.o.ui.views.screens.loginRoute
import com.shahad.o.ui.views.screens.splashRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OTheme {
                val navController = rememberNavController()
                MainScreen(navController)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
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
        }
    }
}
