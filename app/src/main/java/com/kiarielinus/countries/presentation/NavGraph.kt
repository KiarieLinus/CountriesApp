package com.kiarielinus.countries.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kiarielinus.countries.presentation.country_details.CountryDetailsScreen
import com.kiarielinus.countries.presentation.search_country.SearchCountryScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {

    val viewModel: CountryViewModel = hiltViewModel()// Creates an Instance of existing viewModel
    NavHost(
        navController = navController,
        startDestination = Screen.Search.route
    ) {
        composable(
            route = Screen.Search.route
        ) {
            SearchCountryScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.Detail.route
        ) {
            CountryDetailsScreen(navController = navController, viewModel = viewModel)
        }
    }
}