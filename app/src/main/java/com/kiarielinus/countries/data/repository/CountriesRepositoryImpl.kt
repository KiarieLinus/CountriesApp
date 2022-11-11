package com.kiarielinus.countries.data.repository

import com.kiarielinus.countries.data.remote.CountriesApi
import com.kiarielinus.countries.domain.model.CountryInfo
import com.kiarielinus.countries.domain.repository.CountriesRepository
import com.kiarielinus.countries.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class CountriesRepositoryImpl(
    private val api: CountriesApi
) : CountriesRepository {
    override fun getCountriesData(): Flow<Resource<List<CountryInfo>>> = flow {

        var response: List<CountryInfo> = emptyList()
        try {
            emit(Resource.Loading())
            response = api.getCountriesList().body()!!.map { it.toCountryInfo() }
            if (response.isNotEmpty()) {
                emit(Resource.Success(data = response))
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = e.localizedMessage ?: "An unknown error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection"
                )
            )
        }
    }
}