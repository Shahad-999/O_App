package com.shahad.o.ui.util

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
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
        val childCenter = itemInfo.offset - (itemInfo.size / 2)
        this@scrollAndCentralizeItem.animateScrollBy((childCenter+center).toFloat())
    } else {
        this@scrollAndCentralizeItem.animateScrollToItem(index, padding.roundToInt())
    }
    return {}
}


fun Int.isIndexCenter(layoutInfo: LazyListLayoutInfo, padding: Double = 0.0): Boolean {
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

fun NavGraphBuilder.animatedComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    popEnterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? =
        enterTransition,
    popExitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? =
        exitTransition,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {

    composable(
        route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = { scaleIntoContainer() },
        exitTransition = { scaleOutOfContainer()  },
//        popEnterTransition = popEnterTransition,
//        popExitTransition = popExitTransition,
        content = content
    )
}


private fun scaleIntoContainer(
//    direction: ScaleTransitionDirection = ScaleTransitionDirection.INWARDS,
    initialScale: Float = 1.1f
): EnterTransition {
    return scaleIn(
        animationSpec = tween(220, delayMillis = 90),
        initialScale = initialScale
    ) + fadeIn(animationSpec = tween(220, delayMillis = 90))
}

private fun scaleOutOfContainer(
//    direction: ScaleTransitionDirection = ScaleTransitionDirection.OUTWARDS,
    targetScale: Float =  1.1f
): ExitTransition {
    return scaleOut(
        animationSpec = tween(
            durationMillis = 220,
            delayMillis = 90
        ), targetScale = targetScale
    ) + fadeOut(tween(delayMillis = 90))
}
