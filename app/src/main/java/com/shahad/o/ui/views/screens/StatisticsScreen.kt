package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.util.animatedComposable
import com.shahad.o.ui.views.widgets.StatisticsBody

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.statisticsRoute(
    navController: NavHostController
) {
    animatedComposable(Screens.StatisticsScreen.route) {
        StatisticScreen(
            backToHome = navController::navigateUp,
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StatisticScreen(
    modifier: Modifier = Modifier,
//    calendarViewModel: CalendarViewModel = koinViewModel(),
    backToHome: () -> Unit,
) {

    StatisticsBody (
        modifier = modifier,
        backToHome=backToHome
    )
}

