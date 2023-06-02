package com.shahad.o.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Custom Colors
object  Colors{

    val PrimaryLightColor = Color(0xFF4A7995)
    val PrimaryDarkColor = Color(0xFF4A7995)

    val BackgroundLightColor = Color(0xFFFFFFFF)
    val BackgroundDarkColor = Color(0xFF000000)


    val CardLightColor = Color(0xFF8B9FAF)
    val CardDarkColor = Color(0xFF302C2C)


    val Shade1LightColor = Color(0xDE000000)
    val Shade1DarkColor = Color(0xDEFFFFFF)

    val Shade2LightColor = Color(0xAD000000)
    val Shade2DarkColor = Color(0xADFFFFFF)

    val Shade3LightColor = Color(0x61000000)
    val Shade3DarkColor = Color(0x61FFFFFF)



}

@Stable
class OColors(
    primary: Color,
    background: Color,
    card: Color,
    shade1: Color,
    shade2: Color,
    shade3: Color,
    isLight: Boolean
) {

    var primary by mutableStateOf(primary)
        private set
    var background by mutableStateOf(background)
        private set
    var card by mutableStateOf(card)
        private set

    var shade1 by mutableStateOf(shade1)
        private set
    var shade2 by mutableStateOf(shade2)
        private set
    var shade3 by mutableStateOf(shade3)
        private set


    fun update(colors: OColors){
        primary = colors.primary
        background = colors.background
        card = colors.card
        shade1 = colors.shade1
        shade2 = colors.shade2
        shade3 = colors.shade3
    }

}

internal val LocalCustomColors =
    staticCompositionLocalOf<OColors> { error("No CustomColors provided") }

// Composable for custom provider
@Composable
fun ProvideCustomColors(
    colors: OColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalCustomColors provides colorPalette, content = content)
}

val LightThemeColors by lazy {
    OColors(
        primary = Colors.PrimaryLightColor,
        background = Colors.BackgroundLightColor,
        card = Colors.CardLightColor,
        shade1 = Colors.Shade1LightColor,
        shade2 = Colors.Shade2LightColor,
        shade3 = Colors.Shade3LightColor,
        isLight = true
    )
}

val DarkThemeColors by lazy {
    OColors(
        primary = Colors.PrimaryDarkColor,
        background = Colors.BackgroundDarkColor,
        card = Colors.CardDarkColor,
        shade1 = Colors.Shade1DarkColor,
        shade2 = Colors.Shade2DarkColor,
        shade3 = Colors.Shade3DarkColor,
        isLight = false
    )
}