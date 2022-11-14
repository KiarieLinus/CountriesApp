package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiarielinus.countries.R
import com.kiarielinus.countries.presentation.ui.theme.Axiforma
import com.kiarielinus.countries.presentation.ui.theme.Gray500
import com.kiarielinus.countries.presentation.ui.theme.Gray900
import com.kiarielinus.countries.presentation.ui.theme.White
import com.kiarielinus.countries.util.getContinents
import com.kiarielinus.countries.util.getTimeZones

@Composable
fun FiltersScreen(
    isContinentClicked: Boolean,
    isTimeZoneClicked: Boolean,
    onRevealContinent: () -> Unit,
    onRevealTimeZones: () -> Unit,
    submitFilters: () -> Unit,
    onReset: () -> Unit,
    onFilterValueSelected: (String) -> Unit,
    onFilterUnselected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        SheetTitle(header = "Filter")
        FilterHeader(
            title = "Continent",
            imageVector = if (isContinentClicked) Icons.Default.ExpandLess else Icons.Default.ExpandMore
        ) {
            onRevealContinent()
        }
        if (isContinentClicked ) {
            LazyColumn(modifier = Modifier.height(300.dp)) {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                items(getContinents()) { continent ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .padding(bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = continent,
                            fontFamily = Axiforma,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            color = Gray900
                        )
                        val checkedState = remember { mutableStateOf(false) }
                        Checkbox(
                            modifier = Modifier.size(18.dp),
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = it
                                onFilterValueSelected(continent)
                            })
                        if (checkedState.value) onFilterValueSelected(continent) else onFilterUnselected(continent)
                    }
                }
            }
        }
        FilterHeader(
            title = "Time Zone",
            imageVector = if (isTimeZoneClicked ) Icons.Default.ExpandLess else Icons.Default.ExpandMore
        ) {
            onRevealTimeZones()
        }
        if (isTimeZoneClicked) {
            LazyColumn(modifier = Modifier.height(300.dp)) {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                items(getTimeZones()) { timeZone ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .padding(bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = timeZone,
                            fontFamily = Axiforma,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            color = Gray900
                        )
                        val checkedState = remember { mutableStateOf(false) }
                        Checkbox(
                            modifier = Modifier.size(18.dp),
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = it
                            })
                        if (checkedState.value) onFilterValueSelected(timeZone) else onFilterUnselected(timeZone)
                    }
                }
            }

        }
        if (isContinentClicked || isTimeZoneClicked) FilterButtons(
            submitFilters = { submitFilters() },
            resetCheckedButtons = onReset)
    }
}

@Composable
fun TranslationsScreen(
    translations: List<String>,
    selectedTranslation: String,
    onLangSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
    ) {
        SheetTitle(header = "Languages")
        LazyColumn {
            items(translations.sorted()) { translation ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 8.dp)
                        .clickable { onLangSelected(translation) },
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
                        selected = selectedTranslation == translation,
                        onClick = { onLangSelected(translation) }
                    )
                }
            }
        }
    }
}

@Composable
fun SheetTitle(
    header: String
) {
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = header,
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
fun FilterHeader(
    title: String,
    imageVector: ImageVector,
    dropDownClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontFamily = Axiforma,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = Gray900
        )
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            Modifier.clickable { dropDownClicked() },
            tint = Gray500
        )
    }
}

@Composable
fun FilterButtons(
    submitFilters: () -> Unit,
    resetCheckedButtons: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(
            onClick = { resetCheckedButtons() },
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                stringResource(R.string.reset_button),
                fontFamily = Axiforma,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = Gray900
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .weight(2f),
            onClick = { submitFilters() }
        ) {
            Text(
                stringResource(R.string.submit_button),
                fontFamily = Axiforma,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
            )
        }
    }
}

@Preview
@Composable
fun HeaderPrev() {
    FilterHeader(title = "Continent", imageVector = Icons.Default.ExpandMore) {

    }
}

@Preview
@Composable
fun ButtonsPrev() {
    FilterButtons(submitFilters = {}, resetCheckedButtons = {})
}