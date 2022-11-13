package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kiarielinus.countries.presentation.ui.theme.Axiforma
import com.kiarielinus.countries.presentation.ui.theme.Gray500
import com.kiarielinus.countries.presentation.ui.theme.Gray900

@Composable
fun CountryItem(
    flagImageUrl: String,
    countryName: String,
    countryCapital: String,
    onCountryClicked: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCountryClicked()
            }
    ) {
        AsyncImage(
            model = flagImageUrl,
            contentDescription = countryName,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(25)),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = countryName,
                fontFamily = Axiforma,
                fontSize = 14.sp,
                lineHeight = 22.18.sp,
                letterSpacing = (-0.3).sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.W400,
                color = Gray900
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = countryCapital,
                fontFamily = Axiforma,
                fontSize = 14.sp,
                lineHeight = 22.18.sp,
                letterSpacing = (-0.3).sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W400,
                color = Gray500
            )
        }
    }
}