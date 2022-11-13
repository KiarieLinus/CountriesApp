package com.kiarielinus.countries.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiarielinus.countries.domain.model.CountryInfo
import com.kiarielinus.countries.domain.use_cases.UseCases
import com.kiarielinus.countries.presentation.country_details.DetailsState
import com.kiarielinus.countries.presentation.search_country.SearchState
import com.kiarielinus.countries.util.Resource
import com.kiarielinus.countries.util.translationMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    private val _detailsState = mutableStateOf(DetailsState(null))
    val detailsState: State<DetailsState> = _detailsState

    private val _selectedLanguage = mutableStateOf(translationMapper()["eng"]!!)
    val selectedLanguage: State<String> = _selectedLanguage

    init {
        getCountries()
    }

    private var job: Job? = null
    private fun getCountries() {
        viewModelScope.launch {
            when (val resource = useCases.getCountriesList()) {
                is Resource.Error -> {
                    resource.message?.let {
                        _searchState.value = _searchState.value.copy(
                            countries = emptyList(),
                            isLoading = false,
                            error = it
                        )
                    }
                }
                is Resource.Success -> {
                    resource.data?.let { list ->
                        _searchState.value = _searchState.value.copy(
                            countries = list.sortedBy{it.name},
                            isLoading = false,
                            error = ""
                        )
                    }
                }
                is Resource.Loading -> {
                    _searchState.value = _searchState.value.copy(
                        countries = emptyList(),
                        isLoading = true,
                        error = ""
                    )
                }
            }
        }
    }

    fun countryClicked(countryInfo: CountryInfo) {
        _detailsState.value = _detailsState.value.copy(countryInfo = countryInfo)
    }

    fun onLanguageSelected(translation: String) {
        _selectedLanguage.value = translation
    }
}