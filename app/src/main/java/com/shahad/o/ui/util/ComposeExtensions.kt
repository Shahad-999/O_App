package com.shahad.o.ui.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.navigation.NavHostController
import com.shahad.o.ui.navigation.Screens

fun NavHostController.go(source: Screens, destination: Screens) {
    navigate(destination.route) {
        popUpTo(source.route) {
            inclusive = true
        }
    }
}

fun DrawScope.circle(
    color: Color,
    strokeWidth: Float
) {
    drawArc(
        color = color,
        startAngle = 0f,
        sweepAngle = 360f,
        useCenter = false,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
    )

}