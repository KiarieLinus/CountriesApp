package com.kiarielinus.countries.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF000F24),
    primaryVariant = Color(0xFF000F24),
    secondary = Teal200,
    background = Color(0xFF000F24)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFFFFFFF),
    primaryVariant = Color(0xFFFFFFFF),
    secondary = Teal200,
    background = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF000000),
    secondaryVariant = Color(0xFFFFFFFF)
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CountriesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}