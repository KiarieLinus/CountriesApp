package com.kiarielinus.countries.presentation.search_country

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kiarielinus.countries.domain.model.CountryInfo
import com.kiarielinus.countries.util.translationMapper
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainLayout(
    countries: List<CountryInfo>,
    onCountryClicked: (country: CountryInfo) -> Unit,
    selectedLanguage: String,
    onLangSelected: (String) -> Unit,
    onContinentReveal: () -> Unit,
    isContinentClicked: Boolean,
    onTimeZoneReveal: () -> Unit,
    isTimeZoneClicked: Boolean,
    submitFilters: () -> ListMode,
    onFilterValueSelected: (String) -> Unit,
    onFilterUnselected: (String) -> Unit,
    getFilteredList: () -> List<CountryInfo>,
    getSearchedList: (String) -> List<CountryInfo>,
    selectedFilters: () -> List<String>,
    onReset: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var currentBottomSheet: BottomSheetScreen? by remember {
        mutableStateOf(null)
    }
    var currentListMode: ListMode by remember {
        mutableStateOf(ListMode.Language)
    }

    val backdropState =
        rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
    val closeSheet: () -> Unit = {
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

    var currentCountries by remember {
        mutableStateOf(countries)
    }

    val translations by remember { mutableStateOf(translationMapper().map { it.value }) }
    val languageKeyMap = translationMapper()
        .map { it.value to it.key }.toMap()
    val key = languageKeyMap[selectedLanguage]!!

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
    BackdropScaffold(
        headerHeight = 0.dp,
        stickyFrontLayer = false,
        appBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(
                        top = 16.dp
                    )
            ) {
                SearchPane(setSearchMode = {
                    currentListMode = it
                })
                ConfigPane(
                    language = key.uppercase(Locale.getDefault()),
                    onLangChange = {
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
                modifier = Modifier.fillMaxSize()
            ) {
                val scrollState = rememberLazyListState()
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    state = scrollState
                ) {
                    when (currentListMode) {
                        is ListMode.Filters -> {
                            currentCountries = getFilteredList()

                            Log.d("ListMode", "Filter")
                        }
                        is ListMode.Search -> {
                            val countryName = (currentListMode as ListMode.Search).name
                            if (countryName.isBlank()) {
                                ListMode.Language
                            }
                            currentCountries = getSearchedList(countryName)

                            Log.d("ListMode", "Search")
                        }
                        is ListMode.Language -> {
                            currentCountries = countries

                        }
                    }


                    val groupedCountries = currentCountries.groupBy { it.name.first() }
                    groupedCountries.forEach { entry ->
                        item { Text(text = entry.key.toString()) }
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
                    submitFilers = { currentListMode = submitFilters() },
                    onFilterUnselected = { onFilterUnselected(it) },
                    onFilterValueSelected = { onFilterValueSelected(it) },
                    onReset = {
                        currentListMode = it
                        onReset()},
                    selectedFilters = selectedFilters
                )
            }
        },
        scaffoldState = backdropState,
    )
}


sealed class ListMode {
    class Search(val name: String) : ListMode()
    object Filters : ListMode()
    object Language : ListMode()
}