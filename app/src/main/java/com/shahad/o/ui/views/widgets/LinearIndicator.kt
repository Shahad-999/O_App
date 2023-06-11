package com.shahad.o.ui.views.widgets


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahad.o.ui.theme.OTheme

@Composable
fun LinearIndicator(
    modifier: Modifier = Modifier,
    currentIndex: Int,
    total: Int,
) {
    val trackColor = Color(0xFFDBE6EC)
    val color = OTheme.colors.primary
    Canvas(
        modifier = modifier
            .height(20.dp)
            .fillMaxWidth()
    ) {
        val height = size.height
        val width = size.width
        drawLine(
            color = trackColor,
            Offset(0F * width, height),
            Offset(1F * width, height),
            strokeWidth = height,
            cap = StrokeCap.Round
        )

        drawLine(
            color = color,
            Offset(0F * width, height),
            Offset((currentIndex / total.toFloat()) * width, height),
            strokeWidth = height,
            cap = StrokeCap.Round
        )
    }

}


@Preview(showBackground = true)
@Composable
fun LinearIndicatorPreview() {
    OTheme {
        Surface {
            LinearIndicator(currentIndex = 5, total = 10)
        }
    }
}
