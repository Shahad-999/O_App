package com.shahad.o.ui.views.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.shahad.o.domain.models.Statistics
import com.shahad.o.ui.theme.OTheme
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatisticsBody(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit,
    onSetStartDate: (LocalDate) -> Unit,
    onSetEndDate: (LocalDate) -> Unit,
    endDate: LocalDate,
    startDate: LocalDate,
    statistics: List<Statistics>
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
            DateRangeSelector(
                modifier = Modifier.padding(horizontal = 24.dp),
                onSetStartDate=  onSetStartDate,
                onSetEndDate= onSetEndDate,
                startDate= startDate,
                endDate = endDate,
            )
            StatisticsChart(
                statistics = statistics, modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 40.dp)
                    .padding(start = 8.dp)
            )
        }

    }

}