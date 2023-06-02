package com.shahad.o.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

object OTheme {
    val colors: OColors
        @Composable
        get() = LocalCustomColors.current

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes
}

@Composable
fun OTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) DarkThemeColors else LightThemeColors

    ProvideCustomColors(colors = colors) {
        MaterialTheme(
            typography = Typography,
            content = content
        )
    }
}