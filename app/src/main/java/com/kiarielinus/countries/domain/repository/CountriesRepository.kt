package com.kiarielinus.countries.domain.repository

import com.kiarielinus.countries.domain.model.CountryInfo
import com.kiarielinus.countries.util.Resource

interface CountriesRepository {
    suspend fun getCountriesData(): Resource<List<CountryInfo>>
}