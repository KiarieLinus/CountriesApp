package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiarielinus.countries.presentation.ui.theme.Axiforma
import com.kiarielinus.countries.presentation.ui.theme.Gray900
import com.kiarielinus.countries.presentation.ui.theme.White

@Composable
fun FiltersScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Filter",
            fontFamily = Axiforma,
            fontWeight = FontWeight.W700,
            fontSize = 20.sp,
            lineHeight = 32.9.sp,
            color = Gray900,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun TranslationsScreen(
    translations: List<String>,
    selectedValue: String,
    onLangSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Languages",
            fontFamily = Axiforma,
            fontWeight = FontWeight.W700,
            fontSize = 20.sp,
            lineHeight = 32.9.sp,
            color = Gray900,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
        LazyColumn {
            items(translations.sorted()) { translation ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = translation,
                        fontFamily = Axiforma,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Gray900
                    )
                    RadioButton(
                        modifier = Modifier.size(18.dp),
                        selected = selectedValue == translation,
                        onClick = { onLangSelected(translation) }
                    )
                }
            }
        }
    }
}