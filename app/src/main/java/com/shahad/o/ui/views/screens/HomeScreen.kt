package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.util.animatedComposable
import com.shahad.o.ui.viewModels.HomeViewModel
import com.shahad.o.ui.views.widgets.HomeBody
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.homeRoute(
    navController: NavHostController
) {

    fun navToSetting() =
        navController.navigate(Screens.SettingScreen.route)

    fun navToRecord() =
        navController.navigate(Screens.RecordScreen.route)

    animatedComposable(Screens.HomeScreen.route) {
        HomeScreen(
            navToSetting = ::navToSetting,
            navToRecord = ::navToRecord
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navToRecord: () -> Unit,
    navToSetting: () -> Unit,
) {
    val todayStatus: Boolean by viewModel.todayStatus.collectAsState()
    HomeBody(
        modifier = modifier,
        imageUrl = viewModel.userData?.profilePictureUrl,
        name = viewModel.userData?.userName?.split(" ")?.firstOrNull(),
        onClickStart = navToRecord,
        onClickSetting = navToSetting,
        isUploadToday = todayStatus
    )

}
