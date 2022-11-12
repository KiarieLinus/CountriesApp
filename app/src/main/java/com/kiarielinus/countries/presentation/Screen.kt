package com.kiarielinus.countries.presentation

sealed class Screen(val route: String) {
    object Search: Screen(route = "search_country_screen")
    object Detail: Screen(route = "country_details_screen")
}