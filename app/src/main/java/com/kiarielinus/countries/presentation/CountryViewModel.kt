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
import com.kiarielinus.countries.util.getContinents
import com.kiarielinus.countries.util.getTimeZones
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

    private val _isContinentClicked = mutableStateOf(false)
    val isContinentClicked: State<Boolean> = _isContinentClicked

    private val _isTimeZoneClicked = mutableStateOf(false)
    val isTimeZoneClicked: State<Boolean> = _isTimeZoneClicked

    private val _filterList = mutableStateOf(mutableListOf<String>())
    private val _countriesInFilter = mutableStateOf(mutableListOf<CountryInfo>())

    private val _noTimeZone = mutableStateOf(false)
    val noTimeZone: State<Boolean> = _noTimeZone

    private val _noContinent = mutableStateOf(false)
    val noContinent: State<Boolean> = _noContinent

    init {
        getCountries()
    }

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
                            countries = list.sortedBy { it.name },
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

    fun updateIsContinentClicked() {
        _isContinentClicked.value = !_isContinentClicked.value
        if (_isTimeZoneClicked.value) {
            _isTimeZoneClicked.value = false
        }
    }

    fun updateIsTimeZoneClicked() {
        _isTimeZoneClicked.value = !_isTimeZoneClicked.value
        if (_isContinentClicked.value) {
            _isContinentClicked.value = false
        }
    }

    fun selectFilter(filter: String) {
        _filterList.value.add(filter)
        if(getContinents().contains(filter)){
            _noContinent.value = false
        }
        if(getTimeZones().contains(filter)){
            _noContinent.value = false
        }
    }

    fun unselectFilter(filter: String) {
        _filterList.value.remove(filter)
    }

    fun getFilteredList(): List<CountryInfo> {
        val filters = _filterList.value.distinct()
        val countries = _searchState.value.countries

        if (!getContinents().any { continent ->
                filters.any {
                    it == continent
                }
            }) {
            _noContinent.value = true
        }

        if (!getTimeZones().any { timeZone ->
                filters.any {
                    it == timeZone
                }
            }) {
            _noTimeZone.value = true
        }

        if (!(_noContinent.value && _noTimeZone.value)) {
            countries.forEach { country ->
                if (
                    country.continent.any { continent ->
                        filters.any {
                            continent == it
                        }
                    } && country.timezone.any { timeZone ->
                        filters.any {
                            timeZone == it
                        }
                    }
                ) {
                    _countriesInFilter.value.add(country)
                }
            }
        }
        return _countriesInFilter.value.distinct().sortedBy { it.name }
    }

    fun getSearched(query: String): List<CountryInfo> {
        val countries = _searchState.value.countries
        return countries.filter { it.name.contains(query, ignoreCase = true) }
    }

    fun getSelectedFilters(): List<String> {
        return _filterList.value.distinct()
    }

    fun clearFilters() {
        _filterList.value = mutableListOf()
        _noContinent.value = false
        _noTimeZone.value = false
    }
}