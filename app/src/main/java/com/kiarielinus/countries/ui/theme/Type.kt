package com.kiarielinus.countries.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kiarielinus.countries.R

val ElsieSwashCaps = FontFamily(
    Font(R.font.elsie_swash_caps_black)
)

val Axiforma = FontFamily(
    Font(R.font.kastelov_axiforma_bold, FontWeight.W700),
    Font(R.font.kastelov_axiforma_regular, FontWeight.W400),
    Font(R.font.kastelov_axiforma_medium, FontWeight.W500),
    Font(R.font.kastelov_axiforma_light, FontWeight.W300)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)