package com.kiarielinus.countries.data.remote

import com.kiarielinus.countries.data.remote.response.CountriesDtoItem
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {

    @GET("v3.1/all")
    suspend fun getCountriesList(): List<CountriesDtoItem>

    companion object{
        const val BASE_URL = "https://restcountries.com/"
    }
}