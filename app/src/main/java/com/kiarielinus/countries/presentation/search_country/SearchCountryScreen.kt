package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.kiarielinus.countries.presentation.Screen
import com.kiarielinus.countries.presentation.ui.theme.*
import dagger.Lazy
import kotlinx.coroutines.launch

enum class SheetContent {
    TRANSLATIONS, FILTERS, NONE
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchCountryScreen(
    navController: NavController,
    viewModel: SearchCountryViewModel
) {
    val scaffoldState =
        rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(
                initialValue = BottomSheetValue.Collapsed
            )
        )

    val scope = rememberCoroutineScope()
    var sheetContent by remember { mutableStateOf(SheetContent.NONE) }
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .padding(horizontal = 24.dp)
                    .padding(
                        top = 16.dp
                    )
            ) {
                SearchPane()
                ConfigPane(
                    language = "EN",
                    onLangChange = {
                        sheetContent = SheetContent.TRANSLATIONS
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    },
                    onFilterChange = { sheetContent = SheetContent.FILTERS }
                )
            }
        },
        sheetPeekHeight = 0.dp,
        sheetContent = {
            when (sheetContent) {
                SheetContent.TRANSLATIONS -> {
                    TranslationsSheet()
                }
                SheetContent.FILTERS -> {
                    FiltersSheet()
                }
                SheetContent.NONE -> {
                    scope.launch {
                        scaffoldState.bottomSheetState.collapse()
                    }
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val state = viewModel.searchState.value
            val scrollState = rememberLazyListState()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                state = scrollState
            ) {
                val countries = state.countries
                val groupedCountries = countries.groupBy { it.name.first() }
                if (!state.isLoading && state.error.isBlank()) {
                    groupedCountries.forEach { entry ->
                        item { Text(text = entry.key.toString(), color = Gray900) }
                        items(entry.value) { item ->
                            CountryItem(
                                flagImageUrl = item.flagUrl,
                                countryName = item.name,
                                countryCapital = item.capital
                            ) {
                                viewModel.countryClicked(item)
                                navController.navigate(Screen.Detail.route)
                            }
                        }
                    }
                } else {
                    item { Text(text = state.error, color = Gray900) }
                }
            }

        }
    }
}

@Composable
fun FiltersSheet() {
    TODO("Add the Filters for BottomSheet")
}

@Composable
fun TranslationsSheet() {
    TODO("Add translation for BottomSheet")
}

@Composable
fun SearchPane() {
    Column {
        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(9f)
            ) {
                Text(text = "Explore", fontFamily = ElsieSwashCaps, fontSize = 24.sp)
                Text(text = ".", fontFamily = ElsieSwashCaps, fontSize = 24.sp, color = Orange)
            }
            Icon(
                imageVector = Icons.Outlined.LightMode,
                contentDescription = "",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            value = "",
            onValueChange = {},
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Gray100,
                            RoundedCornerShape(4.dp)
                        )
                ) {
                    IconButton(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .size(16.dp),
                            tint = Gray500
                        )
                    }
                    Text(
                        text = "Search Country",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(9f),
                        fontSize = 16.sp,
                        fontFamily = Axiforma,
                        fontWeight = FontWeight.W300,
                        letterSpacing = (-0.3).sp,
                        lineHeight = 24.67.sp,
                        color = Gray500
                    )
                }
                innerTextField()
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ConfigPane(
    language: String,
    onLangChange: () -> Unit,
    onFilterChange: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ConfigButton(
            text = language,
            imgVector = Icons.Outlined.Language
        ) { onLangChange() }
        ConfigButton(
            text = "Filter",
            imgVector = Icons.Outlined.FilterAlt
        ) { onFilterChange() }
    }
}

@Composable
fun ConfigButton(
    text: String,
    imgVector: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFFFFFF),
            contentColor = Color(0xFF000000),
        )
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
            color = Color(0xFF000000)
        )
    }
}

@Composable
fun CountryItem(
    flagImageUrl: String,
    countryName: String,
    countryCapital: String,
    onCountryClicked: () -> Unit
) {

    Row(
        verticalAlignment = CenterVertically,
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
                textAlign = TextAlign.Center,
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

@Preview(
    showBackground = true
)
@Composable
fun SearchPanePrev() {
    SearchPane()
}

@Preview(
    showBackground = true
)
@Composable
fun CountryItemPrev() {
    CountryItem(
        flagImageUrl = "che.ck",
        countryName = "Kenya",
        countryCapital = "Nairobi"
    ) {}
}

@Preview
@Composable
fun ConfigBtnPrev() {
    ConfigButton(text = "Filter", imgVector = Icons.Outlined.FilterAlt) {}
}