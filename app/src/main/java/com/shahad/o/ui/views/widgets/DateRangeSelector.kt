package com.shahad.o.ui.views.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateRangeSelector(
    modifier: Modifier = Modifier,
    onSetStartDate: (LocalDate) -> Unit,
    onSetEndDate: (LocalDate) -> Unit,
    endDate: LocalDate,
    startDate: LocalDate,
) {
    Row(
        modifier = modifier
    ) {
        DatePicker(
            modifier = Modifier.weight(1f),
            currentDate = startDate,
            onSelectDate = onSetStartDate
        )
        Spacer(modifier = Modifier.width(32.dp))


        DatePicker(
            modifier = Modifier.weight(1f),
            currentDate = endDate,
            onSelectDate = onSetEndDate
        )
    }
}