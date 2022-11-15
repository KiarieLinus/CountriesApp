package com.kiarielinus.countries.presentation.search_country

import androidx.compose.runtime.Composable

@Composable
fun SheetLayout(
    currentScreen: BottomSheetScreen,
    closeSheet: () -> Unit,
    selectedValue: String,
    onLangSelected: (String) -> Unit,
    onRevealContinent: () -> Unit,
    isContinentClicked: Boolean,
    isTimeZoneClicked: Boolean,
    onRevealTimeZone: () -> Unit,
    submitFilers: () -> Unit,
    onFilterUnselected: (String) -> Unit,
    onFilterValueSelected: (String) -> Unit,
    onReset: (ListMode) -> Unit,
    selectedFilters: () -> List<String>
) {
    BottomSheetWithCloseDialog(onClosePressed = closeSheet) {
        when (currentScreen) {
            is BottomSheetScreen.FiltersScreen -> FiltersScreen(
                isContinentClicked = isContinentClicked,
                isTimeZoneClicked = isTimeZoneClicked,
                onRevealTimeZones = onRevealTimeZone,
                onRevealContinent = onRevealContinent,
                submitFilters = { submitFilers() },
                onFilterUnselected = { onFilterUnselected(it) },
                onFilterValueSelected = { onFilterValueSelected(it) },
                onReset = {
                    closeSheet()
                    onReset(it)
                          },
                selectedFilters = selectedFilters
            )
            is BottomSheetScreen.TranslationsScreen -> TranslationsScreen(
                translations = currentScreen.translations,
                selectedTranslation = selectedValue
            ) { onLangSelected(it) }
        }
    }
}

sealed class BottomSheetScreen {
    class FiltersScreen() : BottomSheetScreen()
    class TranslationsScreen(val translations: List<String>) :
        BottomSheetScreen()
}