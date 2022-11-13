package com.kiarielinus.countries.presentation.search_country

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun SheetLayout(
    currentScreen: BottomSheetScreen,
    closeSheet: () -> Unit,
    selectedValue: String,
    onLangSelected: (String) -> Unit
) {
    BottomSheetWithCloseDialog(onClosePressed = closeSheet) {
        when (currentScreen) {
            is BottomSheetScreen.FiltersScreen -> TODO()
            is BottomSheetScreen.TranslationsScreen -> TranslationsScreen(
                translations = currentScreen.translations,
                selectedValue = selectedValue
            ) { onLangSelected(it) }
        }
    }
}

sealed class BottomSheetScreen {
    class FiltersScreen() : BottomSheetScreen()
    class TranslationsScreen(val translations: List<String>) :
        BottomSheetScreen()
}