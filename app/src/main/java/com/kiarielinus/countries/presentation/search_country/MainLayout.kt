package com.kiarielinus.countries.presentation.search_country

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kiarielinus.countries.domain.model.CountryInfo
import com.kiarielinus.countries.presentation.ui.theme.Gray900
import com.kiarielinus.countries.util.translationMapper
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainLayout(
    countries: List<CountryInfo>,
    screenHeight: Int,
    onCountryClicked: (country: CountryInfo) -> Unit,
    selectedLanguage: String,
    onLangSelected: (String) -> Unit,
    onContinentReveal: () -> Unit,
    isContinentClicked: Boolean,
    onTimeZoneReveal: () -> Unit,
    isTimeZoneClicked: Boolean,
    submitFilters: () -> List<String>,
    onFilterValueSelected: (String) -> Unit,
    onReset: () -> Unit,
    onFilterUnselected: (String) -> Unit
) {
    var peekHeight by remember {
        mutableStateOf(56.dp)
    }
    val scope = rememberCoroutineScope()
    var currentBottomSheet: BottomSheetScreen? by remember {
        mutableStateOf(null)
    }
    val backdropState =
        rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
    val closeSheet: () -> Unit = {
        peekHeight = 0.dp
        currentBottomSheet = null
        scope.launch {
            backdropState.reveal()
        }
    }
    val openSheet: (BottomSheetScreen) -> Unit = {
        scope.launch {
            currentBottomSheet = it
            backdropState.conceal()
        }
    }
    var isLangClicked by remember {
        mutableStateOf(false)
    }
    peekHeight =
        if (!isContinentClicked && !isTimeZoneClicked && !isLangClicked) (screenHeight * 0.9).dp else (screenHeight * 0.3).dp

    val translations by remember { mutableStateOf(translationMapper().map { it.value }) }
    val languageKeyMap = translationMapper()
        .map { it.value to it.key }.toMap()
    val key = languageKeyMap[selectedLanguage]!!

    var filterList = emptyList<String>()
    //LOG ALL DISTINCT UTC & CONTINENTS IN THE DATA OBJECTS
//    val timezones = mutableListOf<String>()
//    countries.forEach { country ->
//        timezones.addAll(country.timezone)
//    }
//    val distinctTimeZone = timezones.distinct()
//    Log.d("MainLayoutUTC", distinctTimeZone.sorted().toString())

//    val continents = mutableListOf<String>()
//    countries.forEach {
//        continents.addAll(it.continent)
//    }
//    val distinctContinents = continents.distinct()
//    Log.d("MainLayoutContinent", distinctContinents.sorted().toString())
    //Data above is saved in util file FilterValues
    Log.d("CheckFilters", filterList.toString())
    BackdropScaffold(
        headerHeight = 0.dp,
        stickyFrontLayer = false,
        appBar = {
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
                    language = key.uppercase(Locale.getDefault()),
                    onLangChange = {
                        isLangClicked = true
                        openSheet(
                            BottomSheetScreen.TranslationsScreen(
                                translations
                            )
                        )
                    },
                    onFilterChange = {
                        openSheet(
                            BottomSheetScreen.FiltersScreen(

                            )
                        )
                    }
                )
            }
        },
        backLayerContent = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val scrollState = rememberLazyListState()
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    state = scrollState
                ) {
                    val groupedCountries = countries.groupBy { it.name.first() }
                    groupedCountries.forEach { entry ->
                        item { Text(text = entry.key.toString(), color = Gray900) }
                        items(entry.value) { item ->
                            CountryItem(
                                flagImageUrl = item.flagUrl,
                                countryName = if (selectedLanguage == translationMapper()["eng"]!!) item.name
                                else
                                    if (item.translations?.containsKey(key) == true) item.translations.getValue(
                                        key
                                    ) else item.name,
                                countryCapital = item.capital
                            ) {
                                onCountryClicked(item)
                            }
                        }
                    }
                    item { Spacer(modifier = Modifier.height(24.dp)) }
                }
            }
        },
        frontLayerContent = {
            currentBottomSheet?.let { currentSheet ->
                SheetLayout(
                    currentSheet,
                    closeSheet,
                    selectedLanguage,
                    onLangSelected,
                    onRevealContinent = onContinentReveal,
                    isContinentClicked = isContinentClicked,
                    isTimeZoneClicked = isTimeZoneClicked,
                    onRevealTimeZone = onTimeZoneReveal,
                    submitFilers = {
                        filterList = submitFilters()
                                   },
                    onFilterValueSelected = onFilterValueSelected,
                    onReset = onReset,
                    onFilterUnselected = onFilterUnselected
                )
            }
        },
        peekHeight = peekHeight,
        scaffoldState = backdropState
    )
}


sealed class ListFilters {
    //TODO(Implement the filters on backLayer Content)
    class Search(val name: String) : ListFilters()
    class Continent(val continent: String) : ListFilters()
    class TimeZone(val timeZone: String) : ListFilters()
}