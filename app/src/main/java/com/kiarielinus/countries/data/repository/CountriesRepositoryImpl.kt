package com.kiarielinus.countries.data.repository

import com.kiarielinus.countries.data.remote.CountriesApi
import com.kiarielinus.countries.domain.model.CountryInfo
import com.kiarielinus.countries.domain.repository.CountriesRepository
import com.kiarielinus.countries.util.Resource
import retrofit2.HttpException
import java.io.IOException

class CountriesRepositoryImpl(
    private val api: CountriesApi
): CountriesRepository{
    override suspend fun getCountriesData(): Resource<List<CountryInfo>> {
        return try{
            Resource.Success(
                data = api.getCountriesList().map {it.toCountryInfo()}
            )
        }catch (e: HttpException){
            Resource.Error(
                message = e.message()
            )
        }catch(e: IOException){
            Resource.Error(
                message = "Couldn't reach server, check your internet connection"
            )
        }
    }
}