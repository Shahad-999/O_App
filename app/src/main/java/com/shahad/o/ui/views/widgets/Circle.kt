package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp

@Composable
fun OCircle(
    modifier: Modifier = Modifier,
    size: Dp,
    circles: DrawScope.() -> Unit
){
    Canvas(modifier = modifier.size(size)) {
        circles(this)
    }
}