package com.kodeco.android.coordplot.country_info.ui.screens.countryinfo

import androidx.compose.runtime.IntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.android.coordplot.country_info.networking.CountryInfoState
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryInfoViewModel(private val repository: CountryRepository) : ViewModel() {
    private val _uiState =
        MutableStateFlow<CountryInfoState>(CountryInfoState.Loading)
    val uiState: StateFlow<CountryInfoState> = _uiState

    private var _counter = mutableIntStateOf(0)
    val counter: IntState = _counter

    init {
        fetchCountryList()
        viewModelScope.launch {
            while (true) {
                delay(1000)
                _counter.value += 1
            }
        }
    }

    fun fetchCountryList(refreshNeeded: Boolean = false) {
        _uiState.value = CountryInfoState.Loading
        viewModelScope.launch {
            val countryListFlow = repository.fetchCountries(refreshNeeded)
            countryListFlow.collect {
                _uiState.value = CountryInfoState.Success(it)
            }
        }
    }
}
