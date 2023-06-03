package com.shahad.o.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

object OTheme {
    val colors: OColors
        @Composable
        get() = LocalOColors.current

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

    val rememberedColors = remember { colors }.apply { update(colors) }

    CompositionLocalProvider(
        LocalOColors provides rememberedColors,
    ) {
        MaterialTheme(
            typography = Typography,
            content = content
        )
    }

}