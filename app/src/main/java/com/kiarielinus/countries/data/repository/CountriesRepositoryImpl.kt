package com.kiarielinus.countries.data.repository

import com.kiarielinus.countries.data.remote.CountriesApi
import com.kiarielinus.countries.data.remote.response.CountriesDtoItem
import com.kiarielinus.countries.domain.model.CountryInfo
import com.kiarielinus.countries.domain.repository.CountriesRepository
import com.kiarielinus.countries.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.Collections.emptyList

class CountriesRepositoryImpl(
    private val api: CountriesApi
) : CountriesRepository {
    override suspend fun getCountriesData(): Resource<List<CountryInfo>> {
//        Resource.Loading<List<CountryInfo>>()
        return try {
            val response = api.getCountriesList()
            Resource.Success(data = response.map { it.toCountryInfo() })
        } catch (e: HttpException) {
            Resource.Error(
                message = e.localizedMessage ?: "An unknown error occurred"
            )
        } catch (e: IOException) {
            Resource.Error(
                message = "Couldn't reach server, check your internet connection"
            )
        }
    }
}