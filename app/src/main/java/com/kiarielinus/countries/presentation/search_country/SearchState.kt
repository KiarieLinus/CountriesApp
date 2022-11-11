package com.kiarielinus.countries.presentation.search_country

import com.kiarielinus.countries.domain.model.CountryInfo

data class SearchState(
    val countries: List<CountryInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)