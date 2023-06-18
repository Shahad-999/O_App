package com.shahad.o.ui.views.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.util.circle

@Composable
fun Smile(
    modifier: Modifier = Modifier,
    baseColor: Color = OTheme.colors.primary,
    smileColor: Color = OTheme.colors.primaryVariant,
) {

    val sweep = remember {
        Animatable(initialValue = 0F)
    }

    Canvas(
        modifier = modifier
    ) {
        circle(
            color = baseColor,
            strokeWidth = 64F
        )
        drawArc(
            color = smileColor,
            startAngle = 180F,
            sweepAngle = sweep.value,
            useCenter = false,
            style = Stroke(width = 64F, cap = StrokeCap.Round),
        )
    }

    LaunchedEffect(Unit) {
        sweep.animateTo(
            targetValue = -180F,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing)
        )
    }

}