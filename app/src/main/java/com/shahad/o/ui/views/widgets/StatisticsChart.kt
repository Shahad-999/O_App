package com.shahad.o.ui.views.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.DefaultDimens
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.textComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf
import com.shahad.o.domain.models.Statistics
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.util.getDaysValueFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatisticsChart(
    modifier: Modifier = Modifier,
    statistics: List<Statistics>
) {
    val chartEntryModel =
        ChartEntryModelProducer(listOf(statistics.mapIndexed { _, value ->
            entryOf(
                value.date.toEpochDays(),
                value.percent.toFloat()
            )
        })).requireModel()

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier

    ) {
        Chart(
            modifier= Modifier.fillMaxSize(),
            chart = columnChart(
                spacing = 60.dp,
                columns = listOf(
                    LineComponent(
                        color = OTheme.colors.primary.toArgb(),
                        thicknessDp = DefaultDimens.COLUMN_WIDTH,
                        shape = Shapes.roundedCornerShape(allPercent = DefaultDimens.COLUMN_ROUNDNESS_PERCENT),
                    )
                )
            ),
            model = chartEntryModel,
            startAxis = rememberStartAxis(
                guideline  = null,
                label = textComponent{
                    color = OTheme.colors.shade1.toArgb()
                } ,
            ),
            bottomAxis = rememberBottomAxis(
                label = textComponent{
                    color = OTheme.colors.shade1.toArgb()
                } ,
                valueFormatter = getDaysValueFormatter(statistics.associate { statistic -> statistic.date to statistic.percent.toFloat() }),
            ),



        )

    }
}