package com.shahad.o.ui.views.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
//import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
//import com.patrykandpatrick.vico.compose.chart.Chart
//import com.patrykandpatrick.vico.compose.chart.line.lineChart
//import com.patrykandpatrick.vico.compose.style.ChartStyle
//import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.shahad.o.ui.theme.OTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatisticsBody(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
    onSetStartDate: (LocalDate) -> Unit,
    onSetEndDate: (LocalDate) -> Unit,
    endDate: LocalDate,
    startDate: LocalDate
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            NavigationAppBar(

                text = "",
                backToHome = backToHome,
                modifier = modifier.background(OTheme.colors.background)
            )
        }
    ) {
        Column(
            modifier
                .fillMaxSize()
                .background(OTheme.colors.background)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Select range to see a graph",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(500),
                    color = OTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                DatePicker(
                    modifier = Modifier.weight(1f),
                    currentDate =startDate,
                    onSelectDate = onSetStartDate
                )
                Spacer(modifier = Modifier.width(32.dp))


                DatePicker(
                    modifier = Modifier.weight(1f),
                    currentDate = endDate,
                    onSelectDate = onSetEndDate
                )
            }

//            val chartEntryModel = entryModelOf(4f, 12f, 8f, 16f)
//            Chart(
//                chart= lineChart(),
//                model = chartEntryModel,
//                startAxis = rememberStartAxis(),
//                bottomAxis = rememberBottomAxis(),
//            )
//
        }
    }
}