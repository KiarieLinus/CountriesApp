package com.kiarielinus.countries.di

import com.kiarielinus.countries.data.remote.CountriesApi
import com.kiarielinus.countries.data.repository.CountriesRepositoryImpl
import com.kiarielinus.countries.domain.repository.CountriesRepository
import com.kiarielinus.countries.domain.use_cases.GetCountriesList
import com.kiarielinus.countries.domain.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesCountriesRepository(api: CountriesApi): CountriesRepository{
        return CountriesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCountriesApi(): CountriesApi{
        return Retrofit.Builder()
            .baseUrl(CountriesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: CountriesRepository): UseCases{
        return UseCases(
            getCountriesList = GetCountriesList(repository)
        )
    }

}