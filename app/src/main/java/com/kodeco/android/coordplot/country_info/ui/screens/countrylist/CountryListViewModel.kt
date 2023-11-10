package com.kodeco.android.coordplot.country_info.ui.screens.countrylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.android.coordplot.country_info.models.Country
import com.kodeco.android.coordplot.country_info.networking.CountryListState
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CountryListViewModel(private val repository: CountryRepository) : ViewModel() {
    private val _uiState =
        MutableStateFlow<CountryListState>(CountryListState.Loading)
    val uiState: StateFlow<CountryListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository
                .countries
                .catch {
                    _uiState.value = CountryListState.Error(it)
                }
                .collect {
                    _uiState.value = CountryListState.Success(it)
                }
        }
        fetchCountryList()
    }

    fun fetchCountryList() {
        _uiState.value = CountryListState.Loading

        viewModelScope.launch {
            delay(3000)
            repository.fetchCountries()
        }
    }

    fun favorite(country: Country) {
        viewModelScope.launch {
            repository.favorite(country)
        }
    }
}
