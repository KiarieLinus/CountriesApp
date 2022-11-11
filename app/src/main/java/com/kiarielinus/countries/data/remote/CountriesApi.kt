package com.kiarielinus.countries.data.remote

import com.kiarielinus.countries.data.remote.response.CountriesDto
import retrofit2.http.GET

interface CountriesApi {

    @GET("all")
    suspend fun getCountriesList(): CountriesDto

    companion object{
        const val BASE_URL = "https://restcountries.com/v3.1/"
    }
}