package com.kiarielinus.countries.presentation.search_country

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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
    onReset: () -> Unit
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
                onReset = onReset
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