package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.views.widgets.HomeBody


fun NavGraphBuilder.homeRoute(
    navController: NavHostController
) {
    composable(Screens.HomeScreen.route) {
        HomeScreen()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    HomeBody(
        modifier = modifier
    )

}
