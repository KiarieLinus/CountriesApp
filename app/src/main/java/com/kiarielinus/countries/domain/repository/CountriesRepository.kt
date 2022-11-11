package com.kiarielinus.countries.domain.repository

import com.kiarielinus.countries.domain.model.CountryInfo
import com.kiarielinus.countries.util.Resource
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    fun getCountriesData(): Flow<Resource<List<CountryInfo>>>
}