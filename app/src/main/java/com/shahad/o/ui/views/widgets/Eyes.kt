package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.shahad.o.ui.theme.OTheme

@Composable
fun Eyes(
    modifier: Modifier = Modifier,
    baseColor: Color = OTheme.colors.primary,
    smileColor: Color = OTheme.colors.primaryVariant,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Canvas(
            modifier = Modifier
                .weight(1F)
                .aspectRatio(1F)
        ) {
            drawCircle(baseColor)
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.15F))
        Canvas(
            modifier = Modifier
                .weight(1F)
                .aspectRatio(1F)
        ) {
            drawCircle(baseColor)
        }
    }

}