package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shahad.o.util.Record
import com.shahad.o.ui.navigation.Screens
import com.shahad.o.ui.viewModels.RecordsViewModel
import com.shahad.o.ui.views.widgets.RecordBody
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.recordRoute(
    navController: NavHostController
) {

    composable(Screens.RecordScreen.route) {
        RecordScreen(
            onClickBack = navController::navigateUp
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecordScreen(
    modifier: Modifier = Modifier,
    viewModel: RecordsViewModel = koinViewModel(),
    onClickBack: () -> Unit
) {
    val records: List<Record> by viewModel.records.collectAsState()
    val currentIndex: Int by viewModel.currentIndex.collectAsState()

    RecordBody(
        modifier = modifier,
        records = records,
        currentIndex = currentIndex,
        onClickBack = onClickBack
    )
}
