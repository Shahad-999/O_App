package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.shahad.o.R
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.states.QuestionsState
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.util.animatedComposable
import com.shahad.o.ui.viewModels.QuestionsViewModel
import com.shahad.o.ui.views.widgets.LoadingView
import com.shahad.o.ui.views.widgets.NavigationAppBar
import com.shahad.o.ui.views.widgets.QuestionsBody
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.questionsRoute(
    navController: NavHostController
) {
    animatedComposable(Screens.QuestionsScreen.route) {
        QuestionsScreen(
            backToHome = navController::navigateUp,
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun QuestionsScreen(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
    questionsViewModel: QuestionsViewModel = koinViewModel()
) {
    val questions by questionsViewModel.records.collectAsState()
    val isChange by questionsViewModel.isChanged.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            NavigationAppBar(
                text = stringResource(R.string.questions),
                backToHome = backToHome,
                actions = {
                    if (isChange) {
                        Text(
                            text = stringResource(R.string.save),
                            modifier = Modifier
                                .clickable {
                                    questionsViewModel.onClickSave()
                                }
                        )
                    }
                }
            )
        },
        containerColor = OTheme.colors.background,
    ) {
        when (questions) {
            QuestionsState.Initial -> {}
            is QuestionsState.LoadedQuestions -> {
                QuestionsBody(
                    modifier = Modifier.padding(it),
                    questions = (questions as QuestionsState.LoadedQuestions).questions,
                    onQuestionChange = questionsViewModel::onQuestionChange,
                    onPositiveAnswerChange = questionsViewModel::onPositiveAnswerChange
                )
            }

            QuestionsState.LoadingQuestions -> LoadingView()
        }

    }

}
