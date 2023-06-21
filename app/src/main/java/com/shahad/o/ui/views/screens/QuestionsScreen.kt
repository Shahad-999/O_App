package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.R
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.views.widgets.NavigationAppBar
import com.shahad.o.util.log


fun NavGraphBuilder.questionsRoute(
    navController: NavHostController
) {

    composable(Screens.QuestionsScreen.route) {
        QuestionsScreen(
            backToHome = navController::navigateUp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            NavigationAppBar(
                text= stringResource(R.string.questions),
                backToHome = backToHome,
                actions = {
                     if(true) {
                         Text(
                             text = stringResource(R.string.save),
                             modifier = Modifier
                                 .clickable { "SAVE".log() }
                         )
                     }
                }
            )
        },
        content = {

        },
        containerColor = OTheme.colors.background,
    )
}
