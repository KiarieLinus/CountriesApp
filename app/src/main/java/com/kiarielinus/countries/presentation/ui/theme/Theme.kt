package com.kiarielinus.countries.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = BlueLight,
    onPrimary = Black2,
    secondary = Color.Black,
    secondaryVariant = Teal300,
    onSecondary = Color.Black,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Black2,
)

private val DarkColorPalette = darkColors(
    primary = BlueDark,
    primaryVariant = Color.White,
    onPrimary = Color.White,
    secondary = Black1,
    onSecondary = Color.White,
    error = RedErrorLight,
    background = BlueDark,
    onBackground = Color.White,
    surface = BlueDark,
    onSurface = Color.White,
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