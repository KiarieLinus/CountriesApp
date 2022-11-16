package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiarielinus.countries.presentation.ui.theme.Axiforma

@Composable
fun ConfigButton(
    text: String,
    imgVector: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Icon(imageVector = imgVector, contentDescription = "Configuration Button Image")
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontFamily = Axiforma,
            fontWeight = FontWeight.W500,
            lineHeight = 19.25.sp,
            letterSpacing = (-0.3).sp,
            textAlign = TextAlign.Center,
//            color = Color(0xFF000000)
        )
    }
}