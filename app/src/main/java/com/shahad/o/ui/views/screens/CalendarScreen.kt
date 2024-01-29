package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.util.animatedComposable
import com.shahad.o.ui.viewModels.CalendarViewModel
import com.shahad.o.ui.views.widgets.CalendarBody
import com.shahad.o.util.Constant.THIRTY_ONE_DAYS
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.calendarRoute(
    navController: NavHostController
) {
    animatedComposable(Screens.CalendarScreen.route) {
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
    calendarViewModel: CalendarViewModel = koinViewModel(),
    backToHome: () -> Unit,
) {
    val year by calendarViewModel.year.collectAsState()
    val monthIndex by calendarViewModel.monthIndex.collectAsState()
    val dayIndex by calendarViewModel.dayIndex.collectAsState()
    val days by calendarViewModel.days.collectAsState(THIRTY_ONE_DAYS)
    val moods by calendarViewModel.moods.collectAsState()
    val date by calendarViewModel.date.collectAsState()

    CalendarBody(
        modifier = modifier,
        backToHome = backToHome,
        date = date,
        year = year,
        monthIndex = monthIndex,
        dayIndex = dayIndex,
        months = calendarViewModel.months,
        days = days,
        moods = moods,
        onYearIncrease = calendarViewModel::increaseYear,
        onYearDecrease = calendarViewModel::decreaseYear,
        onMonthChanged = calendarViewModel::onMonthChanged,
        onDayChanged = calendarViewModel::onDayChanged
    )

}

