package com.shahad.o.ui.util

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.navigation.NavHostController
import com.shahad.o.ui.navigation.Screens
import kotlin.math.roundToInt

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

fun DrawScope.gradiantCircle(
    colors: List<Color>,
    rotateAnimation: Float,
    strokeWidth: Float
) {
    rotate(
        rotateAnimation
    ) {

        drawArc(
            brush = Brush.sweepGradient(colors),
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }

}

fun LazyListState.getIndexOfCenter(padding: Double, delta: Int = 50): Int? {
    val layoutInfo = this.layoutInfo
    val visibleItemsInfo = layoutInfo.visibleItemsInfo

    val viewportWidth =
        layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset
    visibleItemsInfo.forEach {
        if (it.offset == 0 && it.index == 0) {
            return it.index
        }
        if (it.index + 1 == layoutInfo.totalItemsCount &&
            it.offset + it.size <= viewportWidth
        ) {
            return it.index
        }
        val center = this.layoutInfo.viewportEndOffset / 2 - padding.roundToInt()
        val childCenter = it.offset + (it.size / 1.5).toInt()
        val target = childCenter - center
        if (target in -delta..delta) return it.index
    }
    return null
}

suspend fun LazyListState.scrollAndCentralizeItem(index: Int, padding: Double): () -> Unit {
    val itemInfo = this.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
    if (itemInfo != null) {
        val center =
            this@scrollAndCentralizeItem.layoutInfo.viewportEndOffset / 2 - padding.roundToInt()
        val childCenter = itemInfo.offset + (itemInfo.size / 1.5).toInt()
        this@scrollAndCentralizeItem.animateScrollBy((childCenter - center).toFloat())
    } else {
        this@scrollAndCentralizeItem.animateScrollToItem(index)
    }
    return {}
}


fun Int.isIndexCenter(layoutInfo: LazyListLayoutInfo, padding: Double=0.0): Boolean{
    val visibleItemsInfo = layoutInfo.visibleItemsInfo
    val itemInfo = visibleItemsInfo.firstOrNull { it.index == this }
    itemInfo?.let {
        if (itemInfo.index == 0 && itemInfo.offset == 0)
            return true

        val viewportWidth =
            layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset
        if (itemInfo.index + 1 == layoutInfo.totalItemsCount &&
            itemInfo.offset + itemInfo.size <= viewportWidth
        )
            return true

        val delta = 70
        val center = layoutInfo.viewportEndOffset / 2 - padding.roundToInt()
        val childCenter = it.offset + (it.size / 1.5).toInt()
        val target = childCenter - center
        if (target in -delta..delta) return true
    }
    return false
}