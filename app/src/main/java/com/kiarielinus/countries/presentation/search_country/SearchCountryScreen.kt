package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.kiarielinus.countries.presentation.CountryViewModel
import com.kiarielinus.countries.presentation.Screen


@Composable
fun SearchCountryScreen(
    navController: NavController,
    viewModel: CountryViewModel
) {
    val state = viewModel.searchState.value
    val countries = state.countries
    val selectedLanguage = viewModel.selectedLanguage.value

    Surface(
        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colors.background
    ) {
        val screenHeight = LocalConfiguration.current.screenHeightDp
        MainLayout(
            countries = countries,
            screenHeight = screenHeight,
            {
                viewModel.countryClicked(it)
                navController.navigate(Screen.Detail.route)
            },
            selectedLanguage,
            { viewModel.onLanguageSelected(it) },
            onContinentReveal = { viewModel.updateIsContinentClicked() },
            isContinentClicked = viewModel.isContinentClicked.value,
            onTimeZoneReveal = { viewModel.updateIsTimeZoneClicked() },
            isTimeZoneClicked = viewModel.isTimeZoneClicked.value,
            submitFilters = {
                ListMode.Filters
            },
            onFilterValueSelected = { viewModel.selectFilter(it) },
            onFilterUnselected = {
                viewModel.unselectFilter(it)
            },
            getFilteredList = { viewModel.getFilteredList() },
            { viewModel.getSearched(it) },
            selectedFilters = {viewModel.getSelectedFilters()},
            onReset = {viewModel.clearFilters()}
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SearchPanePrev() {
    SearchPane(setSearchMode = { ListMode.Search("") })
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