package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    TODO("Add the Filters for BottomSheet")
}

@Composable
fun TranslationsScreen(
    translations: List<String>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(White)
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Languages",
                    fontFamily = Axiforma,
                    fontWeight = FontWeight.W700,
                    fontSize = 20.sp,
                    lineHeight = 32.9.sp,
                    color = Gray900
                )
            }
        }
        items(translations) { translation ->
            val selectedValue = remember { mutableStateOf("") }
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                    selected = selectedValue.value == translation,
                    onClick = { selectedValue.value = translation }
                )
            }
        }
    }
}