package com.shahad.o.ui.views.widgets

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shahad.o.ui.theme.OTheme

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
){

    val infiniteTransition = rememberInfiniteTransition()
    val sizeAnimation by infiniteTransition.animateValue(
        initialValue = 80.dp,
        targetValue = 140.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    val colorAnimation by infiniteTransition.animateColor(
        initialValue = OTheme.colors.primary.copy(alpha = 0.7F),
        targetValue = OTheme.colors.primary,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )

    )
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(sizeAnimation)
                .border(BorderStroke(2.dp, color = colorAnimation), shape = CircleShape)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorAnimation),
                modifier = Modifier
                    .size(sizeAnimation)
            )
        }
    }
}

@Preview
@Composable
fun LoadingPreview() {
    OTheme {
        Surface {
            LoadingView()
        }
    }
}


