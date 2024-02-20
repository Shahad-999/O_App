package com.shahad.o.ui.views.widgets

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    onSelectDate: (LocalDate) -> Unit
) {
    val picker = DatePickerDialog(
        LocalContext.current,
        R.style.DatePickerTheme,
        { _, year, month, dayOfMonth ->
            onSelectDate(LocalDate(year=year, month = Month.of(month+1),dayOfMonth=dayOfMonth))
        },
        currentDate.year,
        currentDate.monthNumber - 1,
        currentDate.dayOfMonth,
    )
    Box(
        modifier = modifier
            .height(56.dp)
            .border(
                width = 1.dp,
                color = OTheme.colors.onBackground,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable {

                picker.show()
            },
        contentAlignment = Alignment.Center
    ) {


        AutoResizeText(
            currentDate.toString(),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            fontSizeRange = FontSizeRange(
                min = 10.sp,
                max = 18.sp,
            ),
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                color = OTheme.colors.shade1,
            ),
            textAlign = TextAlign.Center
        )
    }
}