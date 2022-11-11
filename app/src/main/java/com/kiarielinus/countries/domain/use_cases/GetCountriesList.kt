package com.kiarielinus.countries.domain.use_cases

import com.kiarielinus.countries.domain.model.CountryInfo
import com.kiarielinus.countries.domain.repository.CountriesRepository
import com.kiarielinus.countries.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCountriesList(
    private val repository: CountriesRepository
) {
    operator fun invoke(): Flow<Resource<List<CountryInfo>>> {
        return repository.getCountriesData()
    }
}