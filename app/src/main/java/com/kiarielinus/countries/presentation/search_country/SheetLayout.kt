package com.kiarielinus.countries.presentation.search_country

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun SheetLayout(currentScreen: BottomSheetScreen, closeSheet: () -> Unit) {
    BottomSheetWithCloseDialog(onClosePressed = closeSheet) {
        when (currentScreen) {
            is BottomSheetScreen.FiltersScreen -> TODO()
            is BottomSheetScreen.TranslationsScreen -> TranslationsScreen(
                translations = currentScreen.translations
            )
        }
    }
}

sealed class BottomSheetScreen {
    open val height: Dp = 0.dp

    class FiltersScreen(override val height: Dp) : BottomSheetScreen()
    class TranslationsScreen(val translations: List<String>, override val height: Dp) :
        BottomSheetScreen()
}