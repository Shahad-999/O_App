package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.views.widgets.CalendarBody

fun NavGraphBuilder.calendarRoute(
    navController: NavHostController
) {
    composable(Screens.CalendarScreen.route) {
        CalendarScreen(
            backToHome = navController::navigateUp,
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
) {
    CalendarBody(
        modifier = modifier,
        backToHome = backToHome
    )

}

