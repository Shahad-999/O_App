package com.shahad.o.ui.views.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.ui.theme.OTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun CalendarBody(
    modifier: Modifier = Modifier,
    year: Int,
    monthIndex: Int,
    dayIndex: Int,
    days: List<Int>,
    months: List<String>,
    moods: List<String>,
    backToHome: () -> Unit,
    onMonthChanged: (Int) -> Unit,
    onDayChanged: (Int) -> Unit,
    onYearIncrease: () -> Unit,
    onYearDecrease: () -> Unit,
    date: String,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { NavigationAppBar(text = "", backToHome = backToHome, modifier = modifier.background(OTheme.colors.background)) }
    ) {
        Column(
            modifier= Modifier
                .padding(it)
                .background(color = OTheme.colors.primary)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                    .background(color = OTheme.colors.background),
            ) {
                Column {
                    YearPicker(modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                        currentYear = "$year",
                        onClickNext = onYearIncrease,
                        onClickPrevious = onYearDecrease
                    )
                    MonthsList(
                        modifier = Modifier,
                        months = months,
                        monthIndex = monthIndex,
                        onScroll = onMonthChanged
                    )
                    DaysList(
                        modifier = Modifier,
                        moods = moods,
                        initialIndex = dayIndex,
                        days= days,
                        onScroll = onDayChanged
                    )

                }

            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(1f),
                contentAlignment = Alignment.Center
            ){

                Text(
                    date,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            LazyColumn {
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
//@Preview(
//    showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
//    showBackground = true
//)
@Composable
fun CalendarPreview() {
    OTheme {
        Surface {
            CalendarBody(
                year = 2014,
                monthIndex = 4595,
                dayIndex = 4511,
                days = listOf(),
                months = listOf(),
                moods = listOf(),
                backToHome = {},
                onMonthChanged = {},
                onDayChanged = {},
                onYearIncrease = {},
                onYearDecrease = {},
                date = "date"
            )
        }
    }
}
