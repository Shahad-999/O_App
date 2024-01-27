package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.states.RecordScreenState
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.util.animatedComposable
import com.shahad.o.ui.viewModels.RecordsViewModel
import com.shahad.o.ui.views.widgets.LoadingView
import com.shahad.o.ui.views.widgets.QuestionsView
import com.shahad.o.ui.views.widgets.ResultBody
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.recordRoute(
    navController: NavHostController
) {

    animatedComposable(Screens.RecordScreen.route) {
        RecordScreen(
            backToHome = navController::navigateUp,
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecordScreen(
    modifier: Modifier = Modifier,
    viewModel: RecordsViewModel = koinViewModel(),
    backToHome: () -> Unit,
) {
    val records: RecordScreenState by viewModel.records.collectAsState()
    val currentIndex: Int by viewModel.currentIndex.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OTheme.colors.primaryVariant)
            .padding(24.dp)
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clickable { backToHome() },
            tint = Color(0xFFDBE6EC)
        )

        when (records) {
            RecordScreenState.Initial -> {
            }

            is RecordScreenState.LoadedQuestions -> {
                QuestionsView(
                    modifier = Modifier,
                    questions = (records as RecordScreenState.LoadedQuestions).questions,
                    currentIndex = currentIndex,
                    onClickYes = viewModel::onClickYes,
                    onClickNo = viewModel::onClickNo
                )
            }

            RecordScreenState.LoadingQuestions -> LoadingView(modifier = Modifier)
            is RecordScreenState.Result -> {
                with(records as RecordScreenState.Result) {
                    ResultBody(text = this.text, image = this.image, modifier = Modifier)
                }
            }
        }
    }
    LaunchedEffect(key1 = records) {
        if (records is RecordScreenState.Result) {
            delay(2000)
            backToHome()
        }
    }

}
