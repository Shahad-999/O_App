package com.shahad.o.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Custom Colors
object Colors {

    val PrimaryLightColor = Color(0xFF4A7995)
    val PrimaryDarkColor = Color(0xFF4A7995)


    val PrimaryVariantLightColor = Color(0xFF7FAECA)
    val PrimaryVariantDarkColor = Color(0xFF7FAECA)

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
    primaryVariant: Color,
    background: Color,
    card: Color,
    shade1: Color,
    shade2: Color,
    shade3: Color,
    gradiantColors: List<Color> = listOf(
        primary,
        primaryVariant,
        primary,
        primaryVariant,
        primary,
        primaryVariant,
    ),
    var isLight: Boolean
) {

    var primary by mutableStateOf(primary)
        private set
    var primaryVariant by mutableStateOf(primaryVariant)
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
    var gradiantColors by mutableStateOf(gradiantColors)
        private set


    fun update(colors: OColors) {
        primary = colors.primary
        primaryVariant = colors.primaryVariant
        background = colors.background
        card = colors.card
        shade1 = colors.shade1
        shade2 = colors.shade2
        shade3 = colors.shade3
    }
}

fun OColors.copy(): OColors {
    val primary = this.primary
    val primaryVariant = this.primaryVariant
    val background = this.background
    val card = this.card
    val shade1 = this.shade1
    val shade2 = this.shade2
    val shade3 = this.shade3
    val gradiantColors = this.gradiantColors.toList()
    val isLight = this.isLight

    return OColors(
        primary = primary,
        primaryVariant = primaryVariant,
        background = background,
        card = card,
        shade1 = shade1,
        shade2 = shade2,
        shade3 = shade3,
        gradiantColors = gradiantColors,
        isLight = isLight
    )
}
internal val LocalOColors = staticCompositionLocalOf { LightThemeColors }


val LightThemeColors by lazy {
    OColors(
        primary = Colors.PrimaryLightColor,
        primaryVariant = Colors.PrimaryVariantLightColor,
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
        primaryVariant = Colors.PrimaryVariantDarkColor,
        background = Colors.BackgroundDarkColor,
        card = Colors.CardDarkColor,
        shade1 = Colors.Shade1DarkColor,
        shade2 = Colors.Shade2DarkColor,
        shade3 = Colors.Shade3DarkColor,
        isLight = false
    )
}
