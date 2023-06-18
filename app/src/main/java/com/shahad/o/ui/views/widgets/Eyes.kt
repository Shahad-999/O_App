package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.shahad.o.ui.theme.OTheme
import kotlinx.coroutines.delay

@Composable
fun Eyes(
    modifier: Modifier = Modifier,
    onCompleteFlipping: () -> Unit
) {

    val sweep = remember { mutableStateOf(180F) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Eye(
            modifier = Modifier
                .weight(1F)
                .aspectRatio(1F),
            sweep = sweep.value
        )

        Spacer(modifier = Modifier.fillMaxWidth(0.15F))

        Eye(
            modifier = Modifier
                .weight(1F)
                .aspectRatio(1F),
            sweep = sweep.value
        )

    }

    LaunchedEffect(key1 = Unit) {
        delay(700)
        repeat(4) {
            delay(500)
            sweep.value = if (sweep.value > 0) -180F else 180F
        }
        delay(700)
        onCompleteFlipping()
    }
}

@Composable
fun Eye(
    modifier: Modifier = Modifier,
    baseColor: Color = OTheme.colors.primary,
    smileColor: Color = OTheme.colors.primaryVariant,
    sweep: Float
){
    Canvas(
        modifier = modifier
    ) {
        drawCircle(baseColor)
        drawArc(
            color = smileColor,
            startAngle = 90F,
            sweepAngle = sweep,
            useCenter = true,
            size = Size(size.width, size.height),
        )
    }

}

@Preview(showBackground = true)
@Composable
fun EyesPreview() {
    OTheme {
        Surface {
            Eyes(onCompleteFlipping = {})
        }
    }
}
