package com.shahad.o.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentMillSecond(): Long = Clock.System.now().atStartOfDay().toEpochMilliseconds()
fun getCurrentDate(): LocalDate =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

@RequiresApi(Build.VERSION_CODES.O)
fun getDaysValueFormatter(data: Map<LocalDate, Float>): AxisValueFormatter<AxisPosition.Horizontal.Bottom> {
    val days = data.keys.associateBy { day -> day.toEpochDays().toFloat() }
    return AxisValueFormatter{ value, _ ->
            var date = days[value]
            if (date == null) {
                date = value.toInt().toDate()
            }
            date.toDDMMMFormat()
        }
}

